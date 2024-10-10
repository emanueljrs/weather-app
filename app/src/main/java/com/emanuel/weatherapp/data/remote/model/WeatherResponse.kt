package com.emanuel.weatherapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
//    @SerializedName("clouds")
//    val clouds: Clouds,
//    val base: String,
//    val dt: Int,
//    val rain: Rain?,
//    val sys: Sys,
//    val visibility: Int,
)

data class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

data class Weather(
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("main")
    val main: String
)

data class Wind(
    @SerializedName("speed")
    val speed: Double,
//    val deg: Int,
//    val gust: Double
)

data class Main(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
//    val grnd_level: Int
)

data class Rain(
    val oneHour: Double
)

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)

data class Clouds(
    @SerializedName("clouds")
    val all: Int
)