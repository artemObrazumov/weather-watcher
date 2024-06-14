package com.artemobrazumov.domain.test.usecase

import com.city.domain.models.city.CityPagingItem
import com.city.domain.models.result.GetCitiesResult
import com.city.domain.repository.CityRepository
import com.city.domain.usecase.GetCitiesUseCase
import io.mockk.coEvery
import io.mockk.mockk
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
        } returns GetCitiesResult.Failure

        Assert.assertEquals(
            getCityUseCase(),
            GetCitiesResult.Failure
        )
    }

    @Test
    fun `should return expected empty city result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val getCityUseCase = GetCitiesUseCase(mockCityRepository)

        val expectedCitiesResult = emptyList<CityPagingItem>()

        coEvery {
            mockCityRepository.getCities()
        } returns GetCitiesResult.Success(expectedCitiesResult)

        Assert.assertEquals(
            getCityUseCase(),
            GetCitiesResult.Success(expectedCitiesResult)
        )
    }

    @Test
    fun `should return expected city result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val getCityUseCase = GetCitiesUseCase(mockCityRepository)

        val expectedCitiesResult = listOf(
            CityPagingItem.City(id = 0, city = "Moscow"),
            CityPagingItem.City(id = 0, city = "Novosibirsk"),
            CityPagingItem.City(id = 0, city = "Yekaterinburg")
        )

        coEvery {
            mockCityRepository.getCities()
        } returns GetCitiesResult.Success(expectedCitiesResult)

        Assert.assertEquals(
            getCityUseCase(),
            GetCitiesResult.Success(expectedCitiesResult)
        )
    }
}