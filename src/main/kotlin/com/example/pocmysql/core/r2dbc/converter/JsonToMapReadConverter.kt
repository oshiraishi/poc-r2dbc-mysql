package com.example.pocmysql.core.r2dbc.converter

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.io.IOException
import java.util.HashMap

@ReadingConverter
class JsonToMapReadConverter(
        val objectMapper: ObjectMapper
) : Converter<String, Map<String, Any>> {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)
    override fun convert(source: String): Map<String, Any>? {
        try {
            val typeRef = object : TypeReference<HashMap<String, Any>>() {}
            val map = objectMapper.readValue(source, typeRef)
            logger.info("READED VALUE FROM MYSQL: $map")
            return map
        } catch (e: IOException) {
            logger.error("Problem while parsing JSON: {}", source, e)
        }
        return HashMap<String, Any>()
    }
}