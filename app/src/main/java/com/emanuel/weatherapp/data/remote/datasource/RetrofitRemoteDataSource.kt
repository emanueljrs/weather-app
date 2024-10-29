package com.emanuel.weatherapp.data.remote.datasource

import com.emanuel.weatherapp.data.remote.api.GeocodingApi
import com.emanuel.weatherapp.data.remote.api.WeatherAPI
import com.emanuel.weatherapp.data.remote.model.GeocodingResponse
import com.emanuel.weatherapp.data.remote.model.WeatherResponse
import javax.inject.Inject

class RetrofitRemoteDataSource @Inject constructor(
    private val weatherAPI: WeatherAPI,
    private val geocodingApi: GeocodingApi
) : RemoteDataSource {

    override suspend fun getCurrentWeather(lat: Float, lng: Float): WeatherResponse {
        return weatherAPI.getCurrentWeather(lat, lng)
    }

    override suspend fun getLatLonWithCityName(cityName: String): GeocodingResponse {
        return geocodingApi.getLatLonWithCityName(cityName).first()
    }
}