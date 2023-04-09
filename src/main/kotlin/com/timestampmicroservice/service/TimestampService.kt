package com.timestampmicroservice.service

import com.timestampmicroservice.model.Timestamp
import com.timestampmicroservice.model.TimestampError
import org.springframework.stereotype.Service
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@Service
class TimestampService {
    var dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z")
    val defaultDateFormats = arrayOf(
        "dd-MM-yyyy",
        "d-MM-yyyy",
        "dd-M-yyyy",
        "d-M-yyyy",
    )

    fun currentTimestamp(): Timestamp {

        val nowZonedDateTime = ZonedDateTime.now()

        val now = nowZonedDateTime.toInstant()

        return Timestamp( now.toEpochMilli(),nowZonedDateTime.format(this.dateFormatter),)
    }

    fun givenTimestamp(dateFormat: String): Any {

        return dateFormat.toLongOrNull()?.let { parseUnix(it) }
            ?: dateValidator(dateFormat)?.let { parseDate(it) }
            ?: TimestampError("Invalid date")

    }
    private fun dateValidator(dateFormat: String): LocalDate? {

        for (format in defaultDateFormats) {

            try {

                val formatter = DateTimeFormatter.ofPattern(format)

                return LocalDate.parse(dateFormat, formatter)

            } catch (e: DateTimeParseException) {

                println(e.message)

            }
        }

        return null

    }


    private fun parseDate(localDate: LocalDate): Timestamp {

        val localDateTime = LocalDateTime.of(localDate, LocalTime.MIDNIGHT)

        val zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault())

        return Timestamp( zonedDateTime.toInstant().toEpochMilli(),zonedDateTime.format(dateFormatter).toString(),)

    }


    private fun parseUnix(unixDate: Long): Timestamp {

        val utcDate = ZonedDateTime.ofInstant(
            Instant.ofEpochMilli(unixDate),
            ZoneId.systemDefault(),
        ).format(dateFormatter)

        return Timestamp(unixDate,utcDate)

    }
}