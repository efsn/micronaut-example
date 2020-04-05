package cn.elmi.example.micronaut.function

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("faas.demo.kt")
                .mainClass(Application.javaClass)
                .start()
    }
}