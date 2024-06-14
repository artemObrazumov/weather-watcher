package com.artemobrazumov.domain.test.usecase

import com.city.domain.models.city.CityPagingItem
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

        val city = CityPagingItem.City(0, "Moscow")

        coEvery {
            mockCityRepository.insertCity(city)
        } returns InsertCityResult.Failure

        Assert.assertEquals(
            insertCityUseCase(city),
            InsertCityResult.Failure
        )
    }

    @Test
    fun `should return expected city id result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val insertCityUseCase = InsertCityUseCase(mockCityRepository)

        val city = CityPagingItem.City(0, "Moscow")
        val cityId = 1L

        coEvery {
            mockCityRepository.insertCity(city)
        } returns InsertCityResult.Success(cityId)

        Assert.assertEquals(
            insertCityUseCase(city),
            InsertCityResult.Success(cityId)
        )
    }
}