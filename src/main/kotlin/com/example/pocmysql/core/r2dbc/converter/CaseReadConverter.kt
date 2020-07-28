package com.example.pocmysql.core.r2dbc.converter

import com.example.pocmysql.model.Case
import com.example.pocmysql.model.PaymentDetails
import com.example.pocmysql.model.PaymentDetailsList
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.spi.Row
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.HashMap

@ReadingConverter
class CaseReadConverter(
        val objectMapper: ObjectMapper
) : Converter<Row, Case> {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun convert(source: Row): Case? {
        try {
            var case = Case()
            source.get("id", String::class.java)?.let { case.setId(it) }
            case.description = source.get("description", String::class.java)
            source.get("detail", String::class.java)?.let {
                val mapTypeRef = object : TypeReference<HashMap<String, Any>>() {}
                case.detail = objectMapper.readValue(it, mapTypeRef)
            }
            case.createDateLocalTime = source.get("create_date_local_time", LocalDateTime::class.java)
            case.createdDateOffsetTime = source.get("created_date_offset_time", ZonedDateTime::class.java)
            case.lastUpdateOffsetTime = source.get("last_update_offset_time", ZonedDateTime::class.java)
            source.get("payments", String::class.java)?.let {
                val typeRef = object : TypeReference<List<PaymentDetails>>() {}
                case.payments = objectMapper.readValue(it, typeRef)
            }
            return case
        } catch (e: IOException) {
            logger.error("Problem while parsing JSON: {}", source, e)
        }
        return null
    }
}