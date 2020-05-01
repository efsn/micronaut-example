package cn.elmi.example.micronaut.converter

import io.micronaut.context.annotation.EachBean
import io.micronaut.context.annotation.EachProperty
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Parameter
import io.micronaut.core.convert.ConversionContext
import io.micronaut.core.convert.ConversionService
import io.micronaut.core.convert.TypeConverter
import io.micronaut.core.order.Ordered
import java.net.URI
import java.net.URISyntaxException
import java.time.DateTimeException
import java.time.Duration
import java.time.LocalDate
import java.util.*
import javax.inject.Singleton

@Singleton
class Map2LocalDateConverter : TypeConverter<Map<*, *>, LocalDate> {
    override fun convert(map: Map<*, *>, targetType: Class<LocalDate>, context: ConversionContext): Optional<LocalDate> {
        val day = ConversionService.SHARED.convert(map["day"], Int::class.java)
        val month = ConversionService.SHARED.convert(map["month"], Int::class.java)
        val year = ConversionService.SHARED.convert(map["year"], Int::class.java)

        if (day.isPresent && month.isPresent && year.isPresent) {
            try {
                return Optional.of(LocalDate.of(year.get(), month.get(), day.get()))
            } catch (e: DateTimeException) {
                context.reject(map, e)
            }
        }
        return Optional.empty()
    }
}

@EachProperty("test.datasource")
class DataSourceConfiguration
@Throws(URISyntaxException::class)
constructor(@param:Parameter val name: String) {
    var url = URI("localhost")
}

@EachProperty(value = "rateLimits", list = true)
class RateLimitsConfiguration(@param:Parameter private val index: Int) : Ordered {
    var period: Duration? = null
    var limit: Int? = null

    override fun getOrder() = index
}

@Factory
class DataSourceFactory {

    @EachBean(DataSourceConfiguration::class)
    internal fun dataSource(configuration: DataSourceConfiguration) = configuration.url
}