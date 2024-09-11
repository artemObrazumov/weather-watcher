package com.database.city.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.database.city.room.entity.WeatherLogEntity.Companion.TABLE
import java.time.LocalDateTime

@Entity(tableName = TABLE)
data class WeatherLogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int? = null,
    @ColumnInfo(name = SUCCESSFUL)
    val successful: Boolean,
    @ColumnInfo(name = TIME)
    val time: LocalDateTime,
    @ColumnInfo(name = CITY_ID)
    val cityId: Int,
    @ColumnInfo(name = ERROR_MESSAGE)
    val errorMessage: String? = null,
    @ColumnInfo(name = TEMPERATURE)
    val temperature: Int? = null,
    @ColumnInfo(name = TEMPERATURE_FEELS)
    val temperatureFeels: Int? = null,
    @ColumnInfo(name = TEMPERATURE_MIN)
    val temperatureMin: Int? = null,
    @ColumnInfo(name = TEMPERATURE_MAX)
    val temperatureMax: Int? = null,
    @ColumnInfo(name = PRESSURE)
    val pressure: Int? = null,
    @ColumnInfo(name = HUMIDITY)
    val humidity: Int? = null,
    @ColumnInfo(name = PRESSURE_SEA)
    val pressureSeaLevel: Int? = null,
    @ColumnInfo(name = PRESSURE_GROUND)
    val pressureGroundLevel: Int? = null,
    @ColumnInfo(name = WIND_SPEED)
    val windSpeed: Double? = null,
    @ColumnInfo(name = WIND_DIRECTION)
    val windDirectionDegrees: Int? = null,
    @ColumnInfo(name = WIND_GUST)
    val windGust: Double? = null,
    @ColumnInfo(name = CLOUDS)
    val clouds: Int? = null,
    @ColumnInfo(name = VISIBILITY)
    val visibility: Int? = null,
    @ColumnInfo(name = CITY)
    val city: String? = null
) {
    companion object {
        const val TABLE = "weather_log"
        const val ID = "id"
        const val SUCCESSFUL = "successful"
        const val TIME = "time"
        const val CITY_ID = "city_id"
        const val ERROR_MESSAGE = "error_message"
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
    }
}