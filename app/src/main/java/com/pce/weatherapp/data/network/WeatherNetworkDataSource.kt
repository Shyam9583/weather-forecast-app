package com.pce.weatherapp.data.network

import androidx.lifecycle.LiveData
import com.pce.weatherapp.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    suspend fun fetchCurrentWeather(location: String)
}