package cn.elmi.example.micronaut.function.ali.domain

import io.micronaut.core.annotation.Creator
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.beans.BeanIntrospection
import io.micronaut.core.beans.BeanProperty
import javax.annotation.concurrent.Immutable

@Introspected
data class User(val name: String) {
    var age: Int = 0
}

val introspection = BeanIntrospection.getIntrospection(User::class.java)
val user = introspection.instantiate("arthur")

val property: BeanProperty<User, String> =
    introspection.getRequiredProperty("name", String::class.java)
val name = property.get(user)

@Introspected
@Immutable
class Vehicle @Creator constructor(
    val make: String,
    val model: String,
    val axels: Int
) {
    constructor(
        make: String,
        model: String
    ) : this(make, model, 2)
}

class Business private constructor(
    val name: String
) {
    companion object {
        @Creator
        fun forName(name: String) = Business(name)
    }
}

@Introspected(classes = [User::class])
class UserConfiguration


