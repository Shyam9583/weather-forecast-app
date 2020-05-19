package com.pce.weatherapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pce.weatherapp.data.network.response.CurrentWeatherResponse
import com.pce.weatherapp.internal.NoConnectionException

class WeatherNetworkDataSourceImpl(private val weatherService: WeatherService) :
    WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            _downloadedCurrentWeather.postValue(
                weatherService.getCurrentWeather(
                    location = location
                )
            )
        } catch (e: NoConnectionException) {
            Log.e("weather-service", "NO CONNECTION", e)
        }
    }
}