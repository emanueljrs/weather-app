package com.emanuel.weatherapp.domain.usecases

import com.emanuel.weatherapp.domain.repository.WeatherRepository
import com.emanuel.weatherapp.domain.model.WeatherInfo
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(lat: Float, lng: Float): WeatherInfo {
        return weatherRepository.getWeather(lat, lng)
    }
}