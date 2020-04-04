package cn.elmi.example.micronaut

import javax.inject.Singleton

@Singleton
class GreetingService {
    fun greet(name: String) = "Hello $name"
}