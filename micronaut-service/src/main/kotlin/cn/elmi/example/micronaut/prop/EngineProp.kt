package cn.elmi.example.micronaut.prop

import cn.elmi.example.micronaut.configure.EngineImpl
import io.micronaut.context.annotation.ConfigurationBuilder
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Factory
import java.util.*
import javax.inject.Singleton
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@ConfigurationProperties("external.engine")
class EngineProp {

    @NotBlank
    var manufacturer = "ford"

    @Min(1)
    var cylinders: Int = 0
    var crankShaft = CrankShaft()

    @ConfigurationProperties("crank-shaft")
    class CrankShaft {
        var rodLength = Optional.empty<Double>()
    }
}