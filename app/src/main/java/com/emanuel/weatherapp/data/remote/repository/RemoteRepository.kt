package com.emanuel.weatherapp.data.remote.repository

import com.emanuel.weatherapp.domain.model.WeatherInfo

interface RemoteRepository {

    suspend fun getWeather(lat: Float, lng: Float): WeatherInfo
}