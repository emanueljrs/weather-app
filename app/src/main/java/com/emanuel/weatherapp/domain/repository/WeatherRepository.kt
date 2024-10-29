package com.emanuel.weatherapp.domain.repository

import com.emanuel.weatherapp.domain.model.CityInfo
import com.emanuel.weatherapp.domain.model.WeatherInfo

interface WeatherRepository {

    suspend fun getWeather(lat: Float, lng: Float): WeatherInfo

    suspend fun getCityInfo(cityName: String): CityInfo
}