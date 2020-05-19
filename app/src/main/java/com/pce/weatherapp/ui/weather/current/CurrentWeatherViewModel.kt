package com.pce.weatherapp.ui.weather.current

import androidx.lifecycle.ViewModel
import com.pce.weatherapp.data.repository.WeatherRepository
import com.pce.weatherapp.internal.lazyDeferred

class CurrentWeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    val weather by lazyDeferred { repository.getCurrentWeather() }
    val weatherLocation by lazyDeferred { repository.getWeatherLocation() }
}
