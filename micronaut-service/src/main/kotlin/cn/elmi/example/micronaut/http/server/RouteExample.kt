package cn.elmi.example.micronaut.http.server

import io.micronaut.context.ExecutionHandleLocator
import io.micronaut.web.router.DefaultRouteBuilder
import io.micronaut.web.router.RouteBuilder
import javax.inject.Inject

class RouteExample(
    execution: ExecutionHandleLocator,
    uriNamingStrategy: RouteBuilder.UriNamingStrategy
) : DefaultRouteBuilder(execution, uriNamingStrategy) {
    @Inject
    fun issuesRoutes(issuesAPI: IssuesAPI) =
        GET("/issues/show/{number}", issuesAPI, "issue", Int::class.java)
}