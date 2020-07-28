package com.example.pocmysql.core.r2dbc.converter

import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.spi.Clob
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter

@WritingConverter
class MapToJsonWriteConverter(
        val objectMapper: ObjectMapper
) : Converter<Map<String, Any>, String> {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)
    override fun convert(source: Map<String, Any>): String? {
        val json = objectMapper.writeValueAsString(source)
        logger.info("WROTE VALUE IN MYSQL: $json")
        return  json
    }
}