package com.weather.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class WeatherLogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,
    @ColumnInfo(name = TEMPERATURE)
    val temperature: Int,
    @ColumnInfo(name = TEMPERATURE_FEELS)
    val temperatureFeels: Int,
    @ColumnInfo(name = TEMPERATURE_MIN)
    val temperatureMin: Int,
    @ColumnInfo(name = TEMPERATURE_MAX)
    val temperatureMax: Int,
    @ColumnInfo(name = PRESSURE)
    val pressure: Int,
    @ColumnInfo(name = HUMIDITY)
    val humidity: Int,
    @ColumnInfo(name = PRESSURE_SEA)
    val pressureSeaLevel: Int,
    @ColumnInfo(name = PRESSURE_GROUND)
    val pressureGroundLevel: Int,
    @ColumnInfo(name = WIND_SPEED)
    val windSpeed: Double,
    @ColumnInfo(name = WIND_DIRECTION)
    val windDirectionDegrees: Int,
    @ColumnInfo(name = WIND_GUST)
    val windGust: Double,
    @ColumnInfo(name = CLOUDS)
    val clouds: Int,
    @ColumnInfo(name = VISIBILITY)
    val visibility: Int,
    @ColumnInfo(name = CITY)
    val city: String,
    @ColumnInfo(name = TIME)
    val time: LocalDateTime
) {
    companion object {
        const val TABLE = "weather_log"
        const val ID = "id"
        const val TEMPERATURE = "temperature"
        const val TEMPERATURE_FEELS = "temperature_feels"
        const val TEMPERATURE_MIN = "temperature_min"
        const val TEMPERATURE_MAX = "temperature_max"
        const val PRESSURE = "pressure"
        const val HUMIDITY = "humidity"
        const val PRESSURE_SEA = "pressure_sea"
        const val PRESSURE_GROUND = "pressure_ground"
        const val WIND_SPEED = "wind_speed"
        const val WIND_DIRECTION = "wind_direction"
        const val WIND_GUST = "wind_gust"
        const val CLOUDS = "clouds"
        const val VISIBILITY = "visibility"
        const val CITY = "city"
        const val TIME = "time"
    }
}