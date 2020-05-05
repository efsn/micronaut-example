package cn.elmi.example.micronaut.aop

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.retry.annotation.CircuitBreaker
import io.micronaut.retry.annotation.Retryable
import io.micronaut.validation.Validated
import javax.naming.ServiceUnavailableException

@Validated
class CircuitBreakerExample {
    @CircuitBreaker(reset = "30s")
    fun find() {
    }
}

@Factory
@Validated
class Neo4jDriverFactory {

    @Retryable(ServiceUnavailableException::class)
    @Bean(preDestroy = "close")
    fun buildDriver() {
        // ...
    }
}