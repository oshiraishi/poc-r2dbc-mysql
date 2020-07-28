package com.example.pocmysql.core.r2dbc.converter

import com.example.pocmysql.model.PaymentDetails
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter

@WritingConverter
class PaymentsListToJsonWriteConverter(
        val objectMapper: ObjectMapper
) : Converter<List<PaymentDetails>, String> {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)
    override fun convert(source: List<PaymentDetails>): String? {
        val json = objectMapper.writeValueAsString(source)
        logger.info("WROTE VALUE IN MYSQL: $json")
        return json
    }
}