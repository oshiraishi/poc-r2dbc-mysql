package com.example.pocmysql.repository

import com.example.pocmysql.model.Case
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface CaseRepository : ReactiveCrudRepository<Case, String> {

    @Query("select * from case_tbl where detail->'$.points' = :point")
    fun findAllByDetailPointsEquals(point: Int ): Flux<Case>

}