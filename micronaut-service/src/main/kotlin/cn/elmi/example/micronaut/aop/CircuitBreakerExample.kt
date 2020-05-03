package cn.elmi.example.micronaut.aop

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.retry.annotation.CircuitBreaker
import io.micronaut.retry.annotation.Retryable
import javax.naming.ServiceUnavailableException

open class CircuitBreakerExample {
    @CircuitBreaker(reset = "30s")
    open fun find(): Unit {

    }
}

@Factory
class Neo4jDriverFactory {

    @Retryable(ServiceUnavailableException::class)
    @Bean(preDestroy = "close")
    fun buildDriver() {
        // ...
    }
}