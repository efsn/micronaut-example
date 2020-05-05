package cn.elmi.example.micronaut.aop

import io.micronaut.aop.Around
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import io.micronaut.context.annotation.Type
import io.micronaut.validation.Validated
import java.util.*
import javax.inject.Singleton

@Singleton
class NotNullInterceptor : MethodInterceptor<Any, Any> {
    override fun intercept(context: MethodInvocationContext<Any, Any>): Any {
        val nullParam =
            context
                .parameters
                .entries.stream()
                .filter { Objects.isNull(it.value.value) }
                .findFirst()

        return if (nullParam.isPresent) throw IllegalArgumentException("Null parameter [${nullParam.get().key}] not allowed") else context.proceed()
    }
}

@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FILE,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Around
@Type(NotNullInterceptor::class)
annotation class NotNull

@Validated
@Singleton
class NotNullExample {

    @NotNull
    fun doWork(taskName: String?) {
        println("Doing job: $taskName")
    }
}