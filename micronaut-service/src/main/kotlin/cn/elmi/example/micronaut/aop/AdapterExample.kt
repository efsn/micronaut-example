package cn.elmi.example.micronaut.aop

import io.micronaut.context.event.StartupEvent
import io.micronaut.runtime.event.annotation.EventListener
import javax.inject.Singleton

@Singleton
class AdapterExample {

    @EventListener
    fun onStartup(event: StartupEvent) {
        println("startup logic here")
    }
}