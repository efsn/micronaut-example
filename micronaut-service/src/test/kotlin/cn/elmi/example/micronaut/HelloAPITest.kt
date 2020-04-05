package cn.elmi.example.micronaut

import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class HelloAPITest {

    @Inject
    private lateinit var helloClient: HelloClient

    @Test
    fun testGreetingService() {
        assertEquals("Hello Mn", helloClient.hello("Mn").blockingGet())
    }
}