package cn.elmi.example.micronaut.http.server

import io.kotlintest.shouldBe
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class HelloAPITest {
    @Inject
    lateinit var server: EmbeddedServer

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `test hello world response`() {
        val res = client.toBlocking().retrieve("/hello")
        res shouldBe "Hello World"
    }
}