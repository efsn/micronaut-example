package cn.elmi.example.micronaut.aop

import io.micronaut.aop.Introduction
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Type
import io.micronaut.runtime.event.annotation.EventListener
import java.time.LocalDateTime
import javax.inject.Singleton

@Introduction
@Type(StubIntroduction::class)
@Bean
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FILE,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_GETTER
)
annotation class Stub(
    val value: String = ""
)

@Singleton
class StubIntroduction : MethodInterceptor<Any, Any> {
    override fun intercept(context: MethodInvocationContext<Any, Any>): Any? =
        context.getValue<Any>(Stub::class.java, context.returnType.type).orElse(null)
}

@Stub
interface StubExample {
    @get:Stub("10")
    val number: Int

    val date: LocalDateTime?
}