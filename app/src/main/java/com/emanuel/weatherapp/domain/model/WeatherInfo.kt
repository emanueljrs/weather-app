package com.emanuel.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherInfo(
    val cityName: String = "",
    val dayOfWeek: String = "",
    val currentDate: String = "",
    val temperature: Int = 0,
    val climate: String = "",
    val icon: String = "",
    val humidity: Int = 0,
) : Parcelable