package cn.elmi.example.micronaut.aop

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.micronaut.context.ApplicationContext
import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Test

@MicronautTest
internal class NotNullExampleTest {

    @Test
    fun `should aspect around advice not null`() {
        val ctx = ApplicationContext.run()
        val bean = ctx.getBean(NotNullExample::class.java)
        val ex = shouldThrow<IllegalArgumentException> {
            bean.doWork(null)
        }
        ex.message shouldBe "Null parameter [taskName] not allowed"
        ctx.close()
    }
}