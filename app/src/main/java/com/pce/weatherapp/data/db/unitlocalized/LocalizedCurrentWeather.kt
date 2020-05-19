package com.pce.weatherapp.data.db.unitlocalized

import androidx.room.ColumnInfo

data class LocalizedCurrentWeather(
    @ColumnInfo(name = "current_isDay")
    val isDay: String,
    @ColumnInfo(name = "current_precipitation")
    val precipitation: Double,
    @ColumnInfo(name = "current_temperature")
    val temperature: Double,
    @ColumnInfo(name = "current_visibility")
    val visibility: Double,
    @ColumnInfo(name = "current_weatherCode")
    val weatherCode: Int,
    @ColumnInfo(name = "current_weatherDescriptions")
    val weatherDescriptions: List<String>,
    @ColumnInfo(name = "current_weatherIcons")
    val weatherIcons: List<String>,
    @ColumnInfo(name = "current_windDir")
    val windDir: String,
    @ColumnInfo(name = "current_windSpeed")
    val windSpeed: Double,
    @ColumnInfo(name = "location_country")
    val country: String,
    @ColumnInfo(name = "location_latitude")
    val latitude: String,
    @ColumnInfo(name = "location_localtimeEpoch")
    val localtimeEpoch: Long,
    @ColumnInfo(name = "location_longitude")
    val longitude: String,
    @ColumnInfo(name = "location_name")
    val name: String
)