package cn.elmi.example.micronaut.function.ali.service

import io.kotlintest.shouldBe
import io.kotlintest.specs.AnnotationSpec
import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Replaces
import io.micronaut.context.event.ApplicationEvent
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.context.event.ApplicationEventPublisher
import javax.inject.Inject
import javax.inject.Singleton

@Replaces(JdbcUserService::class)
internal class MockService

class SampleEvent : ApplicationEvent("")

@Singleton
class SampleEventEmitterBean {

    @Inject
    internal lateinit var eventPublisher: ApplicationEventPublisher

    fun publishSampleEvent() {
        eventPublisher.publishEvent(SampleEvent())
    }
}

class SampleEventListener : ApplicationEventListener<SampleEvent> {
    var invocationCounter = 0

    override fun onApplicationEvent(event: SampleEvent?) {
        invocationCounter++
    }
}


