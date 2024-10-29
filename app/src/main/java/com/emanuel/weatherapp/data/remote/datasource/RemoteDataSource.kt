package com.emanuel.weatherapp.data.remote.datasource

import com.emanuel.weatherapp.data.remote.model.GeocodingResponse
import com.emanuel.weatherapp.data.remote.model.WeatherResponse

interface RemoteDataSource {

    suspend fun getCurrentWeather(lat: Float, lng: Float): WeatherResponse

    suspend fun getLatLonWithCityName(cityName: String): GeocodingResponse
}