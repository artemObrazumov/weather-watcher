package com.city.domain.usecase

import com.city.domain.models.result.CityGetResult
import com.city.domain.repository.CityRepository
import javax.inject.Inject

class DeleteCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke(cityId: Int) =
        cityRepository.deleteCity(cityId)
}