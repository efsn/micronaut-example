package cn.elmi.example.micronaut.function.ali.service

import io.micronaut.core.annotation.Introspected
import io.micronaut.validation.Validated
import javax.inject.Singleton
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Singleton
@Validated
class UserService {
    fun greet(@NotBlank name: String) = "hello $name"

    fun greet(@Valid user: User) = "hello ${user.name}"
}

@Introspected
data class User(
    @field:NotBlank val name: String,
    @field:Min(18) val age: Int
)

