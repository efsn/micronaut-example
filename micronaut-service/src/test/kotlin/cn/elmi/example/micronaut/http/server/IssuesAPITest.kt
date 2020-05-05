package cn.elmi.example.micronaut.http.server

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer

internal class IssuesAPITest : StringSpec() {
    private val embeddedServer = autoClose(ApplicationContext.run(EmbeddedServer::class.java))
    private val client = autoClose(embeddedServer.applicationContext.createBean(HttpClient::class.java, embeddedServer.url))

    init {
        "test issue" {
            val body = client.toBlocking().retrieve("/issues/12")
            body shouldNotBe null
            body shouldBe "Issue # 12!"
        }

        "test issue with invalid integer" {
            val e = shouldThrow<HttpClientResponseException> {
                client.toBlocking().exchange<Any>("/issues/hello")
            }
            e.status.code shouldBe 400
        }

        "test issue without number" {
            val e = shouldThrow<HttpClientResponseException> {
                client.toBlocking().exchange<Any>("/issues/")
            }
            e.status.code shouldBe 404
        }
    }
}