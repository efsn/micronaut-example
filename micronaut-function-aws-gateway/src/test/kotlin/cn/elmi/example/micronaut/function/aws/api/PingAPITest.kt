package cn.elmi.example.micronaut.function.aws.api

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
internal class PingAPITest(
    private val embeddedServer: EmbeddedServer
) {

    @Test
    fun `should ping with pong`() =
        embeddedServer.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.url).use {
            assertEquals(HttpStatus.OK, it.toBlocking().exchange<String>("/ping").status)
        }

}
