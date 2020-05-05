package cn.elmi.example.micronaut.aop

import io.micronaut.retry.annotation.Retryable
import io.micronaut.validation.Validated
import reactor.core.publisher.Flux
import javax.inject.Singleton

@Validated
@Singleton
class RetryAdviceExample {

    @Retryable
    fun list(num: Int) {
        if (num == 0) throw IllegalArgumentException()
        println("retry able")
    }

    @Retryable(attempts = "5", delay = "2s")
    fun find(title: String) = 1

    @Retryable(attempts = "\${book.retry.attempts:3}", delay = "\${book.retry.delay:1s}")
    fun get(title: String) = 2

    @Retryable
    fun streams(): Flux<Int> = Flux.empty()
}