package com.city.domain.usecase

import com.city.domain.repository.CityRepository
import javax.inject.Inject

class GetCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke(cityId: Int) = cityRepository.getCity(cityId)
}