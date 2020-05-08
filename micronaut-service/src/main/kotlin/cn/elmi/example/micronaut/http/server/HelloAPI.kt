package cn.elmi.example.micronaut.http.server

import io.micronaut.core.convert.format.Format
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import java.time.ZonedDateTime
import javax.validation.Valid

@Controller("/hello")
class HelloAPI {
    @Get(produces = [MediaType.APPLICATION_JSON])
    fun index() = "Hello World"

    @Post
    fun body(@Body body: String) = body

    @Header
    fun health(@Header header: String) = header

    @Post("/upload")
    fun upload(@Part bytes: Array<Byte>) {

    }

    @Get("/cookieName")
    fun cookieName(@CookieValue("mycookie") myCookie: String) = ""

    @Get("/cookieInferred")
    fun cookieInferred(@CookieValue myCookie: String) = ""

    @Get("/headerName")
    fun headerName(@Header("Content-Type") contentType: String) = contentType

    @Get("/headerInferred")
    fun headerInferred(@Header contentType: String) = contentType
}

@Controller("/api")
@Validated
class BookmarkAPI {
    @Get("/bookmarks/list{?paginationCommand*}")
    fun list(@Valid paginationCommand: Int) = HttpStatus.OK

    @Get("/date")
    fun date(@Header date: ZonedDateTime) = ""

    @Get("/dateFormat")
    fun dateFormat(@Format("dd/MM/yyyy hh:mm:ss a z") @Header date: ZonedDateTime) = ""
}

@Controller("/request")
class MessageAPI {
    @Get("/hello")
    fun hello(request: HttpRequest<*>): HttpResponse<String> {
        val name = request.parameters.getFirst("name").orElse("Nobody")
        return HttpResponse.ok("Hello $name!!").header("X-My-Header", "Foo")
    }

    @Get("/http-response", produces = [MediaType.TEXT_PLAIN])
    fun httpResponse(): HttpResponse<String> = HttpResponse.status<String>(HttpStatus.CREATED).body("success")

    @Status(HttpStatus.CREATED)
    @Get(produces = [MediaType.TEXT_PLAIN])
    fun index() = "success"

    @Get("/http-status")
    fun httpStatus() = HttpStatus.CREATED
}

@Controller("produces")
class ProducesAPI {
    @Get
    fun index() = HttpResponse.ok<Any>().body(
        """
        {"msg":"This is JSON"}
        """.trimIndent()
    )

    @Produces(MediaType.TEXT_HTML)
    @Get("/html")
    fun html() = """
        <html><title><h1>HTML</h1></title></html>
    """.trimIndent()

    @Get("/xml", produces = [MediaType.TEXT_XML])
    fun xml() = "<html><title><h1>XML</h1></title></html>"
}

@Controller("/consumes")
class ConsumesAPI {
    @Post
    fun index(): HttpResponse<Any> = HttpResponse.ok<Any>()

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON)
    @Post("/multiple")
    fun multipleConsumes(): HttpResponse<Any> = HttpResponse.ok<Any>()

    @Post("/member", consumes = [MediaType.TEXT_PLAIN])
    fun consumesMember(): HttpResponse<Any> = HttpResponse.ok<Any>()
}