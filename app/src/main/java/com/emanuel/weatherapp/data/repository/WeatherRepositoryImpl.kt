package com.emanuel.weatherapp.data.repository

import com.emanuel.weatherapp.data.remote.datasource.RemoteDataSource
import com.emanuel.weatherapp.domain.model.CityInfo
import com.emanuel.weatherapp.domain.model.WeatherInfo
import com.emanuel.weatherapp.domain.repository.WeatherRepository
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository {

    override suspend fun getWeather(lat: Float, lng: Float): WeatherInfo {

        val response = remoteDataSource.getCurrentWeather(lat, lng)

        return WeatherInfo(
            cityName = response.name,
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(
                TextStyle.FULL, Locale.getDefault()
            ),
            currentDate = LocalDate.now().dayOfMonth.toString(),
            temperature = response.main.temp.toInt(),
            climate = response.weather[0].main,
            humidity = response.main.humidity
        )
    }

    override suspend fun getCityInfo(cityName: String): CityInfo {
        val response = remoteDataSource.getLatLonWithCityName(cityName)

        return if (response.isNotEmpty()) {
            CityInfo(
                name = response.first().name,
                lat = response.first().lat,
                lon = response.first().lon
            )
        } else {
            CityInfo()
        }
    }
}