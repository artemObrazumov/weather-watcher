package com.artemobrazumov.domain.test.usecase

import com.city.domain.models.city.City
import com.city.domain.models.result.ModifyCityResult
import com.city.domain.repository.CityRepository
import com.city.domain.usecase.ModifyCityUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ModifyCityUseCaseTest {

    @Test
    fun `should return failure result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val modifyCityUseCase = ModifyCityUseCase(mockCityRepository)

        val cityToUpdate = City("Moscow")

        coEvery {
            mockCityRepository.modifyCity(cityToUpdate)
        } returns ModifyCityResult.Failure

        Assert.assertEquals(
            modifyCityUseCase(cityToUpdate),
            ModifyCityResult.Failure
        )
    }

    @Test
    fun `should return expected city id result`() = runTest {
        val mockCityRepository = mockk<CityRepository>()
        val modifyCityUseCase = ModifyCityUseCase(mockCityRepository)

        val cityToUpdate = City("Moscow")

        coEvery {
            mockCityRepository.modifyCity(cityToUpdate)
        } returns ModifyCityResult.Success

        Assert.assertEquals(
            modifyCityUseCase(cityToUpdate),
            ModifyCityResult.Success
        )
    }
}