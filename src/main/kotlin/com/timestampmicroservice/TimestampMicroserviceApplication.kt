package com.timestampmicroservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TimestampMicroserviceApplication

fun main(args: Array<String>) {
    runApplication<TimestampMicroserviceApplication>(*args)
}
