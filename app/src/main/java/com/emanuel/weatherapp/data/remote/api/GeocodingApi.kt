package com.emanuel.weatherapp.data.remote.api

import com.emanuel.weatherapp.BuildConfig
import com.emanuel.weatherapp.data.remote.model.GeocodingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {

    @GET("direct")
    suspend fun getLatLonWithCityName(
        @Query("q") cityName: String,
        @Query("limit") limit: Int = 3,
        @Query("appid") apikey: String = BuildConfig.API_KEY
    ): List<GeocodingResponse>
}