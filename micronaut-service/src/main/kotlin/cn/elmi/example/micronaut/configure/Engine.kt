package cn.elmi.example.micronaut.configure

import cn.elmi.example.micronaut.prop.EngineProp
import io.micronaut.context.annotation.Property
import io.micronaut.core.convert.format.Format
import io.micronaut.core.convert.format.ReadableBytes
import io.micronaut.validation.Validated
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Validated
class Engine {
    @field:Property(name = "external.engine.cylinders")
    protected var cylinders: Int = 0

    @set:Inject
    @setparam:Property(name = "external.engine.manufacturer")
    var manufacturer: String? = null

    fun cylinders(): Int = cylinders
}

@Singleton
class EngineImpl(val prop: EngineProp) : Engine() {
    override var cylinders: Int = 0
        get() = prop.cylinders

    @ReadableBytes
    var maxContentLength = 0

    @Format("yyyy-MM-dd")
    lateinit var date: LocalDate

    val x = "${prop.manufacturer} Engine Starting V${prop.cylinders} [rodLength=${prop.crankShaft.rodLength.orElse(6.0)}]"
}