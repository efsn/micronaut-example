package cn.elmi.example.micronaut.http.server

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/hello")
class HelloAPI {
    @Get(produces = [MediaType.APPLICATION_JSON])
    fun index() = "Hello World"
}