package com.example.pocmysql.service

import com.example.pocmysql.model.Case
import com.example.pocmysql.repository.CaseRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirstOrDefault
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.UUID

@Component
class CaseService(val caseRepository: CaseRepository,
                  val clock: Clock) {

    suspend fun get(id: String): Case? {
        return coroutineScope {
            val case = caseRepository.findById(id)
            case.awaitFirstOrNull()
        }
    }

    suspend fun post(case: Case?): Mono<Case> {
        return coroutineScope {
            if (case != null) {
                case.setId(UUID.randomUUID().toString())
                case.newCase = true
                case.createDateLocalTime = LocalDateTime.now(clock)
                case.createdDateOffsetTime = ZonedDateTime.now(clock)
                caseRepository.save(case)
            } else {
                throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)
            }
        }
    }

    suspend fun put(case: Case?): Mono<Case> {
        return coroutineScope {
            if (case != null) {
                case.lastUpdateOffsetTime = ZonedDateTime.now(clock)
                caseRepository.save(case)
            } else {
                throw ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)
            }
        }
    }

    suspend fun findAllByDetailPointsEquals(point: String?): List<Case> =
            coroutineScope {
                if (point == null) {
                    emptyList()
                } else {
                    caseRepository.findAllByDetailPointsEquals(point.toInt(10))
                            .collectList()
                            .awaitFirstOrDefault(emptyList())
                }

            }
}