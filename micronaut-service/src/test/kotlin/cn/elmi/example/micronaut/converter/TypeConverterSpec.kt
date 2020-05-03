package cn.elmi.example.micronaut.converter

import io.kotlintest.shouldBe
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.PropertySource
import io.micronaut.inject.qualifiers.Qualifiers
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.URI

@MicronautTest
class TypeConverterSpec {

    lateinit var ctx: ApplicationContext

    @BeforeEach
    fun setup() {
        ctx = ApplicationContext.run(
            mapOf(
                "myapp.updateAt" to mapOf(
                    "day" to 28,
                    "month" to 10,
                    "year" to 2020
                )
            )
        )
    }

    @AfterEach
    fun tearDown() = ctx.close()
}

class EachPropertySpec {

    lateinit var ctx: ApplicationContext

    @BeforeEach
    fun setup() {
        ctx = ApplicationContext.run(
            PropertySource.of(
                "test",
                mapOf(
                    "test.datasource.one.url" to "jdbc:mysql://localhost/one",
                    "test.datasource.two.url" to "jdbc:mysql://localhost/two"
                )
            )
        )
    }

    @Test
    fun `test each property`() {
        val beansOfType = ctx.getBeansOfType(DataSourceConfiguration::class.java)
        beansOfType.size shouldBe 2
        val first = ctx.getBean(DataSourceConfiguration::class.java, Qualifiers.byName("one"))
        first.url shouldBe URI("jdbc:mysql://localhost/one")
    }

    @AfterEach
    fun tearDown() = ctx.close()
}