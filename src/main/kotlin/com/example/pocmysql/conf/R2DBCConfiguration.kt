package com.example.pocmysql.conf

import com.example.pocmysql.core.r2dbc.converter.CaseReadConverter
import com.example.pocmysql.core.r2dbc.converter.JsonToMapReadConverter
import com.example.pocmysql.core.r2dbc.converter.MapToJsonWriteConverter
import com.example.pocmysql.core.r2dbc.converter.PaymentsListToJsonWriteConverter
import com.example.pocmysql.core.r2dbc.converter.ZoneDateTimeReadConverter
import com.fasterxml.jackson.databind.ObjectMapper
import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration
import dev.miku.r2dbc.mysql.MySqlConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import java.time.Clock

@Configuration
@EnableR2dbcRepositories(basePackages = arrayOf("com.example.pocmysql"))
class R2DBCConfiguration(
        val clock: Clock,
        val objectMapper: ObjectMapper,
        @Value("\${spring.r2dbc.database}") val mySqlDatabase: String,
        @Value("\${spring.r2dbc.host}") val mySqlHost: String,
        @Value("\${spring.r2dbc.port}") val mySqlPort: Int,
        @Value("\${spring.r2dbc.username}") val mySqlUsername: String,
        @Value("\${spring.r2dbc.password}") val mySqlPassword: String
): AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): MySqlConnectionFactory =
            MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
                    .database(mySqlDatabase)
                    .host(mySqlHost)
                    .port(mySqlPort)
                    .username(mySqlUsername)
                    .password(mySqlPassword)
                    .build())

    override fun getCustomConverters(): MutableList<Any> {
        return mutableListOf(ZoneDateTimeReadConverter(clock),
                MapToJsonWriteConverter(objectMapper),
                JsonToMapReadConverter(objectMapper),
                CaseReadConverter(objectMapper),
                PaymentsListToJsonWriteConverter(objectMapper))
    }
}
