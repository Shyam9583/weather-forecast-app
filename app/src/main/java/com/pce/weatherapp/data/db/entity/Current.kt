package com.pce.weatherapp.data.db.entity

import com.google.gson.annotations.SerializedName

data class Current(
    @SerializedName("is_day")
    val isDay: String,
    @SerializedName("precip")
    val precipitation: Double,
    val temperature: Double,
    val visibility: Double,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("weather_descriptions")
    val weatherDescriptions: List<String>,
    @SerializedName("weather_icons")
    val weatherIcons: List<String>,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_speed")
    val windSpeed: Double
)