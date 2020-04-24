package cn.elmi.example.micronaut.converter

import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.ConfigurationProperties
import java.time.LocalDate

@ConfigurationProperties(MyConfigurationProperties.PREFIX)
open class MyConfigurationProperties {
    var updateAt: LocalDate? = null
        protected set

    companion object {
        const val PREFIX = "myapp"
    }
}