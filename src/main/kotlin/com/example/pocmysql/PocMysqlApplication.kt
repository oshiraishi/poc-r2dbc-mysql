package com.example.pocmysql

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class PocMysqlApplication

fun main(args: Array<String>) {
	runApplication<PocMysqlApplication>(*args)
}
