package com.city.data.local.repository

import com.city.domain.models.City
import com.database.city.room.CityDatabase
import com.city.data.local.models.result.CitiesGetResult
import com.city.data.local.models.result.CitiesInsertResult
import com.city.data.local.models.result.CitiesModifyResult
import com.city.data.local.utils.toCity
import com.city.data.local.utils.toCityEntity
import javax.inject.Inject

class CityLocalRoomRepositoryImpl @Inject constructor(
    private val database: CityDatabase
): CityLocalRepository {

    override suspend fun loadCities(): CitiesGetResult {
        return try {
            CitiesGetResult.Success(
                database.cityDao.getCities()
            )
        } catch (e: Exception) {
            CitiesGetResult.Failure
        }
    }

    override suspend fun insertCity(city: City): CitiesInsertResult {
        return try {
            CitiesInsertResult.Success(
                database.cityDao.insertCity(city.toCityEntity())
            )
        } catch (e: Exception) {
            CitiesInsertResult.Failure
        }
    }

    override suspend fun modifyCity(city: City): CitiesModifyResult {
        return try {
            CitiesModifyResult.Success(
                database.cityDao.updateCity(city.toCityEntity()).toLong()
            )
        } catch (e: Exception) {
            CitiesModifyResult.Failure
        }
    }
}