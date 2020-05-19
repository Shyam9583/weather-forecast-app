package com.pce.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.pce.weatherapp.data.db.entity.WeatherLocation
import com.pce.weatherapp.data.db.unitlocalized.LocalizedCurrentWeather

interface WeatherRepository {
    suspend fun getCurrentWeather(): LiveData<LocalizedCurrentWeather>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}