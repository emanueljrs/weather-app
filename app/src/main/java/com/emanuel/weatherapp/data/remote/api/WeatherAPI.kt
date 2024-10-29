package com.emanuel.weatherapp.data.remote.api

import com.emanuel.weatherapp.BuildConfig
import com.emanuel.weatherapp.data.remote.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Float,
        @Query("lon") lng: Float,
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}