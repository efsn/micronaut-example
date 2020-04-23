package cn.elmi.example.micronaut

import io.micronaut.runtime.Micronaut

object Application {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
            .packages("cn.elmi")
            .mainClass(javaClass)
            .environmentPropertySource(false)
            .environmentVariableIncludes("TEST_ENV_ONLY")
            .environmentVariableExcludes("EXCLUDE_ENV")
            .start()
    }
}