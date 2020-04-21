package cn.elmi.example.micronaut.function.ali

import io.micronaut.runtime.Micronaut

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut
            .build()
            .packages("cn.elmi.example.micronaut")
            .eagerInitConfiguration(true)
            .mainClass(Application.javaClass)
            .start()
    }
}