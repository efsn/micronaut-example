package cn.elmi.example.micronaut.function.aws.handler

import com.amazonaws.serverless.exceptions.ContainerInitializationException
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import io.micronaut.function.aws.proxy.MicronautLambdaContainerHandler
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class StreamLambdaHandler : RequestStreamHandler {
    private lateinit var handler: MicronautLambdaContainerHandler

    constructor() {
        try {
            handler = MicronautLambdaContainerHandler()
        } catch (e: ContainerInitializationException) {
            e.printStackTrace()
            throw RuntimeException("Couldn't initialize Micronaut", e)
        }
    }

    @Throws(IOException::class)
    override fun handleRequest(input: InputStream?, output: OutputStream?, context: Context?) =
        handler.proxyStream(input, output, context)
}
