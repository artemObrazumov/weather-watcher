package com.database.city.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.database.city.room.dao.CityDao
import com.database.city.room.entity.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class CityDatabase: RoomDatabase() {
    abstract val cityDao: CityDao
}