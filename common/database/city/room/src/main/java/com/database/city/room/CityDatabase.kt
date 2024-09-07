package com.database.city.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.database.city.room.dao.CityDao
import com.database.city.room.entity.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 4,
    exportSchema = false
)
abstract class CityDatabase: RoomDatabase() {
    abstract val cityDao: CityDao

    companion object {

        private const val CITY_DATABASE_NAME = "cities.db"

        fun create(context: Context) = Room.databaseBuilder(
            context,
            CityDatabase::class.java,
            CITY_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}