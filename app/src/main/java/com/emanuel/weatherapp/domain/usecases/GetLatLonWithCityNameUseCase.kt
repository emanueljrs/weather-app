package com.emanuel.weatherapp.domain.usecases

import com.emanuel.weatherapp.domain.model.CityInfo
import com.emanuel.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetLatLonWithCityNameUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(city: String): CityInfo {
        return weatherRepository.getCityInfo(city)
    }
}