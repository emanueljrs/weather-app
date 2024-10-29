package com.emanuel.weatherapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class GeocodingResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("local_names")
    val localNames: LocalNames,
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("lon")
    val lon: Float,
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String
)

data class LocalNames(
    @SerializedName("pt")
    val pt: String
)