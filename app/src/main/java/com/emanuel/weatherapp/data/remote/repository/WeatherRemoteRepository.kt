package com.emanuel.weatherapp.data.remote.repository

import com.emanuel.weatherapp.data.remote.datasource.RemoteDataSource
import com.emanuel.weatherapp.domain.model.WeatherInfo
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

class WeatherRemoteRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {

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
}