package com.timestampmicroservice.controller

import com.timestampmicroservice.model.Timestamp
import com.timestampmicroservice.service.TimestampService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class Controller(val service: TimestampService) {

    @GetMapping
    fun timestamp(): ResponseEntity<Timestamp> {

        return ResponseEntity.ok(service.currentTimestamp())

    }

    @GetMapping("/{date}")
    fun fixedTimestamp(@PathVariable date: String): ResponseEntity<Any> {

        return ResponseEntity.ok(service.givenTimestamp(date))

    }
}