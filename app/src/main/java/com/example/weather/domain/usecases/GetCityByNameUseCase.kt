package com.example.weather.domain.usecases

import com.example.weather.domain.entities.City
import com.example.weather.datalayer.repository.CitiesRepository
import javax.inject.Inject

class GetCityByNameUseCase @Inject constructor(
    val citiesRepository: CitiesRepository
) {
    suspend operator fun invoke(cityName: String): City {
        val city = citiesRepository.getCityByName(cityName)

    }
}