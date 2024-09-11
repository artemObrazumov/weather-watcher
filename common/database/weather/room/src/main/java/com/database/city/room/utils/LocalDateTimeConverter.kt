package com.database.city.room.utils

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun toDate(dateString: String): LocalDateTime =
        LocalDateTime.parse(dateString)

    @TypeConverter
    fun toDateString(date: LocalDateTime): String =
        date.toString()
}