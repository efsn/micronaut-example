package cn.elmi.example.micronaut.function

import io.micronaut.function.FunctionBean
import io.micronaut.function.executor.FunctionInitializer
import java.util.function.Function

@FunctionBean("demo-function")
class DemoFunction : FunctionInitializer(), Function<Message, Message> {

    override fun apply(msg: Message): Message {
        return msg
    }
}

/**
 * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar
 * where the argument to echo is the JSON to be parsed.
 */
fun main(args: Array<String>) {
    val function = DemoFunction()
    function.run(args) { context -> function.apply(context.get(Message::class.java)) }
}