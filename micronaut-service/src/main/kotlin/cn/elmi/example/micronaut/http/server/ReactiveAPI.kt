package cn.elmi.example.micronaut.http.server

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.validation.Validated
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.ExecutorService
import javax.inject.Named
import javax.inject.Singleton
import javax.validation.constraints.Size

@Controller("/subscribeOn/people")
@Validated
class PersonAPI internal constructor(
    @Named(TaskExecutors.IO) executorService: ExecutorService,
    private val personService: PersonService
) {
    private val scheduler = Schedulers.from(executorService)

    @Get("/{name}")
    fun byName(name: String) = Single.fromCallable { personService.findByName(name) }.subscribeOn(scheduler)

    @Post("/echo", consumes = [MediaType.TEXT_PLAIN])
    fun echo(@Size(max = 1024) @Body text: String) = text

    @Post("/echo-flow", consumes = [MediaType.TEXT_PLAIN])
    fun echoFlow(@Body text: Flowable<String>) =
        text.collect({ StringBuffer() }, { obj, str -> obj.append(str) })
            .map { HttpResponse.ok(it.toString()) }
}

@Singleton
class PersonService {
    fun findByName(name: String) = name
}
