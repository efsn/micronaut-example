package cn.elmi.example.micronaut.function.ali.service

import io.micronaut.test.annotation.MicronautTest
import io.micronaut.validation.validator.Validator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.validation.ConstraintViolationException

@MicronautTest
class UserServiceSpec(
    private val userService: UserService,
    private val validator: Validator
) {

    @Test
    fun `validate user by introspect`() {
        val user = User("", 10)
        val constraintValidator = validator.validate(user)
        assertEquals(2, constraintValidator.size)
    }

    @Test
    fun `user name should be validated`() {
        val exception = assertThrows<ConstraintViolationException> { userService.greet("") }
        assertEquals("greet.name: must not be blank", exception.message)
    }

    @Test
    fun `validate by user with anno`() {
        val user = User("", 10)
        val exception = assertThrows<ConstraintViolationException> { userService.greet(user) }
        assertEquals(2, exception.constraintViolations.size)
    }
}