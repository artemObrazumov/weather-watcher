package com.city.data.local.repository

import com.city.domain.models.City
import com.database.city.room.CityDatabase
import com.city.data.local.models.result.CitiesGetResult
import com.city.data.local.models.result.CitiesInsertResult
import com.city.data.local.models.result.CitiesModifyResult
import com.city.data.local.utils.toCity
import com.city.data.local.utils.toCityEntity
import com.database.city.room.dao.CityDao
import javax.inject.Inject

class CityLocalRoomRepositoryImpl @Inject constructor(
    private val cityDao: CityDao
): CityLocalRepository {

    override suspend fun loadCities(): CitiesGetResult {
        return try {
            CitiesGetResult.Success(
                cityDao.getCities()
            )
        } catch (e: Exception) {
            CitiesGetResult.Failure
        }
    }

    override suspend fun insertCity(city: City): CitiesInsertResult {
        return try {
            CitiesInsertResult.Success(
                cityDao.insertCity(city.toCityEntity())
            )
        } catch (e: Exception) {
            CitiesInsertResult.Failure
        }
    }

    override suspend fun modifyCity(city: City): CitiesModifyResult {
        return try {
            CitiesModifyResult.Success(
                cityDao.updateCity(city.toCityEntity()).toLong()
            )
        } catch (e: Exception) {
            CitiesModifyResult.Failure
        }
    }
}