package cn.elmi.example.micronaut.function.aws.api

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.slf4j.LoggerFactory

@Controller("/ping")
class PingAPI {
    private val log = LoggerFactory.getLogger(javaClass)

    @Get("/")
    fun ping(): String {
        log.trace("receive a ping")
        return """
            {"pong": true}
        """.trimIndent()
    }
}