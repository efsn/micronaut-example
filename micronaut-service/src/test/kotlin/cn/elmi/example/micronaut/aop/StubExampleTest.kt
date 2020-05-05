package cn.elmi.example.micronaut.aop

import io.kotlintest.shouldBe
import io.micronaut.context.ApplicationContext
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Test

@MicronautTest
class StubExampleTest {

    @Test
    fun `should get stub value`() {
        val ctx = ApplicationContext.run()
        val stubExample = ctx.getBean(StubExample::class.java)
        stubExample.number shouldBe 10
        stubExample.date shouldBe null
    }
}