package com.pce.weatherapp.data.network.response

import com.pce.weatherapp.data.db.entity.Current
import com.pce.weatherapp.data.db.entity.WeatherLocation

data class CurrentWeatherResponse(
    val current: Current,
    val location: WeatherLocation
)