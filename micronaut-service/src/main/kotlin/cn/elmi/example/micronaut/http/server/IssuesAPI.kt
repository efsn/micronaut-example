package cn.elmi.example.micronaut.http.server

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/issues")
class IssuesAPI {
    @Get("/{number}")
    fun issue(number: Int) = "Issue # $number!"
}

@Controller("/hello")
class BackwardCompatibleController {
    @Get(uris = ["/{name}", "/person/{name}"])
    fun hello(name: String) = "Hello, $name"
}