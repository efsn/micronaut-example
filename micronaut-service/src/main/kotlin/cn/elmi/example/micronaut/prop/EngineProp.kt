package cn.elmi.example.micronaut.prop

import io.micronaut.context.annotation.ConfigurationInject
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.core.bind.annotation.Bindable
import java.util.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

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

@ConfigurationProperties("conf.engine")
data class EngineConf @ConfigurationInject
constructor(
    @Bindable(defaultValue = "Ford") @NotBlank
    val manufacturer: String,

    @Min(1)
    val cylinders: Int,

    @NotNull
    val crankShaft: CrankShaft
) {
    data class CrankShaft @ConfigurationInject
    constructor(private val rodLength: Double?) {
        fun getRodLength() = Optional.ofNullable(rodLength)
    }
}