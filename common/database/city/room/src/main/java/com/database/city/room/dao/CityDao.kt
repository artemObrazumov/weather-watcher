package com.database.city.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.database.city.room.entity.CityEntity

@Dao
interface CityDao {

    @Insert
    fun insertCity(
        city: CityEntity
    ): Long

    @Query("SELECT * FROM ${CityEntity.TABLE}")
    fun getCities(): List<CityEntity>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCity(
        city: CityEntity
    ): Int
}