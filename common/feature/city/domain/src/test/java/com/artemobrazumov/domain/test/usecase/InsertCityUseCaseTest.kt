package com.artemobrazumov.domain.test.usecase

import com.city.domain.models.city.City
import com.city.domain.models.result.InsertCityResult
import com.city.domain.repository.CityRepository
import com.city.domain.usecase.InsertCityUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class InsertCityUseCaseTest {

    @Test
    fun `should return failure result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val insertCityUseCase = InsertCityUseCase(mockCityRepository)

        val cityToInsert = City("Moscow")

        coEvery {
            mockCityRepository.insertCity(cityToInsert)
        } returns InsertCityResult.Failure

        Assert.assertEquals(
            insertCityUseCase(cityToInsert),
            InsertCityResult.Failure
        )
    }

    @Test
    fun `should return expected city id result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val insertCityUseCase = InsertCityUseCase(mockCityRepository)

        val cityToInsert = City("Moscow")
        val cityId = 1L

        coEvery {
            mockCityRepository.insertCity(cityToInsert)
        } returns InsertCityResult.Success(cityId)

        Assert.assertEquals(
            insertCityUseCase(cityToInsert),
            InsertCityResult.Success(cityId)
        )
    }
}