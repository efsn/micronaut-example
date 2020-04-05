package cn.elmi.example.micronaut

import io.micronaut.runtime.Micronaut

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("cn.elmi")
            .mainClass(Application.javaClass)
            .start()
    }
}