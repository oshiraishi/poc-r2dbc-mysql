package com.example.pocmysql.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Table("case_tbl")
class Case : Persistable<String> {
    @Id
    private var id: String? = null
    var description: String? = null
    var detail: Map<String, Any>? = null
    var payments: List<PaymentDetails>? = null
    @CreatedDate
    var createDateLocalTime: LocalDateTime? = null
    var createdDateOffsetTime: ZonedDateTime? = null
    var lastUpdateOffsetTime: ZonedDateTime? = null

    @Transient
    @JsonIgnore
    var newCase: Boolean = false


    override fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    @JsonIgnore
    override fun isNew(): Boolean {
        return newCase
    }
}