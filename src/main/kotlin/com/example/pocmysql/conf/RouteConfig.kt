package com.example.pocmysql.conf

import com.example.pocmysql.handler.CaseHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RouteConfig {

    @Bean
    fun routerV1(
            caseHandler: CaseHandler
    ) =
            coRouter {
                "/case".nest {
                    GET("/search", accept(MediaType.APPLICATION_JSON), caseHandler::search)
                    GET("/{id}", accept(MediaType.APPLICATION_JSON), caseHandler::get)
                    POST("", accept(MediaType.APPLICATION_JSON), caseHandler::post)
                    PUT("/{id}", accept(MediaType.APPLICATION_JSON), caseHandler::put)
                }
            }

}