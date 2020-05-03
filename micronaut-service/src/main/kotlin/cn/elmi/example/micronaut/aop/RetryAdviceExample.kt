package cn.elmi.example.micronaut.aop

import io.micronaut.retry.annotation.Retryable
import reactor.core.publisher.Flux
import javax.inject.Singleton

@Singleton
class RetryAdviceExample {

    @Retryable
    open fun list(num: Int) {
        if (num == 0) throw IllegalArgumentException()
        println("retry able")
    }

    @Retryable(attempts = "5", delay = "2s")
    open fun find(title: String) = 1

    @Retryable(attempts = "\${book.retry.attempts:3}", delay = "\${book.retry.delay:1s}")
    open fun get(title: String) = 2

    @Retryable
    open fun streams(): Flux<Int> = Flux.empty()
}