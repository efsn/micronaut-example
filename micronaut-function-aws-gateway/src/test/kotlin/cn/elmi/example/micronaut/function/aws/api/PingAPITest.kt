package cn.elmi.example.micronaut.function.aws.api

import io.kotlintest.shouldBe
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Test

@MicronautTest
internal class PingAPITest(
    private val embeddedServer: EmbeddedServer
) {

    @Test
    fun `should ping with pong`() =
        embeddedServer.applicationContext.createBean(HttpClient::class.java, embeddedServer.url).use {
            it.toBlocking().exchange<String>("/ping").status shouldBe HttpStatus.OK
        }
}
