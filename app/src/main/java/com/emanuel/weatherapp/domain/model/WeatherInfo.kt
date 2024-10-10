package com.emanuel.weatherapp.domain.model

data class WeatherInfo(
    val cityName: String,
    val dayOfWeek: String,
    val currentDate: String,
    val temperature: Int,
    val climate: String,
    val humidity: Int,
)