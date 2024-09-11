package com.database.city.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.database.city.room.dao.WeatherLogDao
import com.database.city.room.entity.WeatherLogEntity
import com.database.city.room.utils.LocalDateTimeConverter

@Database(
    entities = [WeatherLogEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class WeatherLogDatabase: RoomDatabase() {
    abstract val weatherLogDao: WeatherLogDao

    companion object {

        private const val WEATHER_LOGS_DATABASE_NAME = "weather_logs.db"

        fun create(context: Context) = Room.databaseBuilder(
            context,
            WeatherLogDatabase::class.java,
            WEATHER_LOGS_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}