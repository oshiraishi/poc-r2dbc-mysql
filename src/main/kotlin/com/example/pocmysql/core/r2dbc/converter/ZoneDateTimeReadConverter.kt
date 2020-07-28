package com.example.pocmysql.core.r2dbc.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime

/**
 * Convert UTC TIMESTAMP column to ZondedDateTime
 */
@ReadingConverter
class ZoneDateTimeReadConverter(
        val clock: Clock
) : Converter<LocalDateTime, ZonedDateTime> {

    override fun convert(source: LocalDateTime): ZonedDateTime? {
        return ZonedDateTime.ofInstant(source, ZoneOffset.UTC, clock.zone)
    }
}
