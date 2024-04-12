package com.database.city.room.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.database.city.room.entity.CityEntity

interface CityDao {

    @Insert
    suspend fun insertCity(
        city: CityEntity
    ): Long

    @Query("SELECT * FROM ${CityEntity.TABLE}")
    suspend fun getCities(): List<CityEntity>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateCity(
        city: CityEntity
    ): Long
}