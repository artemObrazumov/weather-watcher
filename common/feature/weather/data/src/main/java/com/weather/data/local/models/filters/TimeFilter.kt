package com.weather.data.local.models.filters

import java.time.LocalDateTime
import java.time.ZoneOffset

class TimeFilter private constructor(

    val from: LocalDateTime,
    val to: LocalDateTime
) {
    companion object {
        operator fun invoke(from: LocalDateTime, to: LocalDateTime): TimeFilter {
            // FROM time must always be lower that TO time
            if (from.toEpochSecond(ZoneOffset.UTC) >= to.toEpochSecond(ZoneOffset.UTC)) {
                throw IllegalArgumentException("FROM time must always be lower that TO time")
            } else {
                return TimeFilter(from, to)
            }
        }
    }
}
