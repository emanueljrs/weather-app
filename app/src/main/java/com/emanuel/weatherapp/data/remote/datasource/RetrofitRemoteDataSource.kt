package com.emanuel.weatherapp.data.remote.datasource

import com.emanuel.weatherapp.data.remote.api.WeatherAPI
import com.emanuel.weatherapp.data.remote.model.WeatherResponse
import javax.inject.Inject

class RetrofitRemoteDataSource @Inject constructor(
    private val weatherAPI: WeatherAPI
) : RemoteDataSource {

    override suspend fun getCurrentWeather(lat: Float, lng: Float): WeatherResponse {
        return weatherAPI.getCurrentWeather(lat, lng)
    }
}