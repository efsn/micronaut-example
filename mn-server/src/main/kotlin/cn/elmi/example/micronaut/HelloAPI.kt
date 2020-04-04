package cn.elmi.example.micronaut

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/")
class HelloAPI(
    private val greetingService: GreetingService
) {
    @Get("/hello/{name}")
    fun hello(name: String) = greetingService.greet(name)
}