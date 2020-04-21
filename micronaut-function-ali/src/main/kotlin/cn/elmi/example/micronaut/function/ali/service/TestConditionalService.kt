package cn.elmi.example.micronaut.function.ali.service

import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.context.event.BeanInitializedEventListener
import io.micronaut.context.event.BeanInitializingEvent
import javax.annotation.PostConstruct
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
@Requirements(
    Requires(beans = [DataSource::class]),
    Requires(property = "datasource.url")
)
class TestConditionalService(
    internal val dataSource: DataSource
)

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Requirements(
    Requires(beans = [DataSource::class]),
    Requires(property = "datasource.url")
)
annotation class RequiresJdbc

@RequiresJdbc
class JdbcUserService

interface Engine

class V8Engine(var rodLength: Double) : Engine

@Factory
class EngineFactory {
    private lateinit var engine: V8Engine
    private var rodLength = 5.7

    @PostConstruct
    fun initialize() {
        engine = V8Engine(rodLength)
    }

    @Singleton
    fun v8Engine() = engine

    fun setRodLength(rodLength: Double) {
        this.rodLength = rodLength
    }
}

@Singleton
class EngineInitializer : BeanInitializedEventListener<EngineFactory> {
    override fun onInitialized(event: BeanInitializingEvent<EngineFactory>): EngineFactory {
        val engineFactory = event.bean
        engineFactory.setRodLength(6.6)
        return event.bean as EngineFactory
    }
}
