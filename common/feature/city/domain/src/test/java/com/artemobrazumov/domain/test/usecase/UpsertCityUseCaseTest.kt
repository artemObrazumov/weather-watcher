package com.artemobrazumov.domain.test.usecase

import com.city.domain.models.City
import com.city.domain.models.result.UpsertCityResult
import com.city.domain.repository.CityRepository
import com.city.domain.usecase.UpsertCityUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class UpsertCityUseCaseTest {

    @Test
    fun `should return failure result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val upsertCityUseCase = UpsertCityUseCase(mockCityRepository)

        val city = City(0, "Moscow")

        coEvery {
            mockCityRepository.upsertCity(city)
        } returns UpsertCityResult.Failure

        Assert.assertEquals(
            upsertCityUseCase(city),
            UpsertCityResult.Failure
        )
    }

    @Test
    fun `should return expected city id result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val upsertCityUseCase = UpsertCityUseCase(mockCityRepository)

        val city = City(0, "Moscow")
        val cityId = 1L

        coEvery {
            mockCityRepository.upsertCity(city)
        } returns UpsertCityResult.Success(cityId)

        Assert.assertEquals(
            upsertCityUseCase(city),
            UpsertCityResult.Success(cityId)
        )
    }
}