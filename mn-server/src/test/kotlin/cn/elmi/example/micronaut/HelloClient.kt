package cn.elmi.example.micronaut

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single
import javax.validation.constraints.NotBlank

@Client("/")
interface HelloClient {
    @Get("/hello/{name}")
    fun hello(@NotBlank name: String): Single<String>
}