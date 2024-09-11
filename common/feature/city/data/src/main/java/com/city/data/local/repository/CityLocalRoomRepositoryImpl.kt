package com.city.data.local.repository

import com.city.data.local.models.result.CitiesEntityGetResult
import com.city.data.local.models.result.CitiesUpsertResult
import com.city.data.local.models.result.CitiesModifyResult
import com.city.data.local.models.result.CityEntityGetResult
import com.city.data.local.utils.toCityEntity
import com.city.domain.models.City
import com.database.city.room.dao.CityDao
import javax.inject.Inject

class CityLocalRoomRepositoryImpl @Inject constructor(
    private val cityDao: CityDao
): CityLocalRepository {

    override suspend fun loadCities(): CitiesEntityGetResult {
        return try {
            CitiesEntityGetResult.Success(
                cityDao.getCities()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            CitiesEntityGetResult.Failure
        }
    }

    override suspend fun upsertCity(city: City): CitiesUpsertResult {
        return try {
            CitiesUpsertResult.Success(
                cityDao.upsertCity(city.toCityEntity()).toInt()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            CitiesUpsertResult.Failure
        }
    }

    override suspend fun modifyCity(city: City): CitiesModifyResult {
        return try {
            CitiesModifyResult.Success(
                cityDao.updateCity(city.toCityEntity()).toLong()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            CitiesModifyResult.Failure
        }
    }

    override suspend fun getCityById(cityId: Int): CityEntityGetResult {
        return try {
            CityEntityGetResult.Success(
                cityDao.getCityById(cityId)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            CityEntityGetResult.Failure("database error")
        }
    }

    override suspend fun deleteCity(cityId: Int) {
        try {
            cityDao.deleteCity(cityId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}