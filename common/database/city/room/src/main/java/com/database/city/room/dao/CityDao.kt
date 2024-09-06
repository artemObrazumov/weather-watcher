package com.database.city.room.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.database.city.room.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Upsert
    suspend fun upsertCity(
        city: CityEntity
    ): Long

    @Query("SELECT * FROM ${CityEntity.TABLE}")
    fun getCities(): Flow<List<CityEntity>>

    @Query("SELECT * FROM ${CityEntity.TABLE} WHERE ${CityEntity.ID} = :cityId")
    fun getCityById(
        cityId: Int
    ): Flow<CityEntity>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateCity(
        city: CityEntity
    ): Int
}