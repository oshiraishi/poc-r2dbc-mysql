package com.example.pocmysql.handler

import com.example.pocmysql.model.Case
import com.example.pocmysql.service.CaseService
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.queryParamOrNull
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Controller
class CaseHandler(val caseService: CaseService) {

    suspend fun get(request: ServerRequest): ServerResponse {
        val case = caseService.get(request.pathVariable("id"))

        return if(case != null ) {
            ServerResponse.ok().bodyValueAndAwait(case)
        } else {
            ServerResponse.notFound().buildAndAwait()
        }
    }

    suspend fun post(request: ServerRequest): ServerResponse {

        val caseMono = caseService.post(request.bodyToMono(Case::class.java).awaitFirstOrNull())
        val case = caseMono.awaitFirstOrNull()
        return if(case != null ) {
            ServerResponse.ok().bodyValueAndAwait(case)
        } else {
            ServerResponse.notFound().buildAndAwait()
        }
    }

    suspend fun put(request: ServerRequest): ServerResponse {

        val caseMono = caseService.put(request.bodyToMono(Case::class.java).awaitFirstOrNull())
        val case = caseMono.awaitFirstOrNull()
        return if(case != null ) {
            ServerResponse.ok().bodyValueAndAwait(case)
        } else {
            ServerResponse.notFound().buildAndAwait()
        }
    }

    suspend fun search(request: ServerRequest): ServerResponse {
        val cases = caseService.findAllByDetailPointsEquals(request.queryParamOrNull("point"))
        return ServerResponse.ok().bodyValueAndAwait(cases)
    }
}