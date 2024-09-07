package com.artemobrazumov.domain.test.usecase

import com.city.domain.models.City
import com.city.domain.models.result.CitiesGetResult
import com.city.domain.repository.CityRepository
import com.city.domain.usecase.GetCitiesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetCitiesUseCaseTest {

    @Test
    fun `should return failure result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val getCityUseCase = GetCitiesUseCase(mockCityRepository)

        coEvery {
            mockCityRepository.getCities()
        } returns CitiesGetResult.Failure

        Assert.assertEquals(
            getCityUseCase(),
            CitiesGetResult.Failure
        )
    }

    @Test
    fun `should return expected empty city result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val getCityUseCase = GetCitiesUseCase(mockCityRepository)

        val expectedCitiesResult = flowOf(emptyList<City>())

        coEvery {
            mockCityRepository.getCities()
        } returns CitiesGetResult.Success(expectedCitiesResult)

        Assert.assertEquals(
            getCityUseCase(),
            CitiesGetResult.Success(expectedCitiesResult)
        )
    }

    @Test
    fun `should return expected city result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val getCityUseCase = GetCitiesUseCase(mockCityRepository)

        val expectedCitiesResult = flowOf(
            listOf(
                City(id = 0, name = "Moscow"),
                City(id = 0, name = "Novosibirsk"),
                City(id = 0, name = "Yekaterinburg")
            )
        )

        coEvery {
            mockCityRepository.getCities()
        } returns CitiesGetResult.Success(expectedCitiesResult)

        Assert.assertEquals(
            getCityUseCase(),
            CitiesGetResult.Success(expectedCitiesResult)
        )
    }
}