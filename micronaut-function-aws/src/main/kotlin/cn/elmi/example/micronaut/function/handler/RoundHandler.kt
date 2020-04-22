package cn.elmi.example.micronaut.function.handler

import io.micronaut.context.env.Environment
import io.micronaut.function.aws.MicronautRequestHandler
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class MathService {
    fun round(input: Float?) = input?.roundToInt() ?: Int.MIN_VALUE
}

class RoundHandler : MicronautRequestHandler<Float, Int>() {

    @Inject
    private lateinit var mathService: MathService

    @Inject
    private lateinit var env: Environment

    override fun execute(input: Float?): Int = mathService.round(input)
}