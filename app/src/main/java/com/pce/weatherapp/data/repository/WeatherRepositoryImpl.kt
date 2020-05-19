package com.pce.weatherapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.pce.weatherapp.data.db.dao.CurrentWeatherDao
import com.pce.weatherapp.data.db.dao.WeatherLocationDao
import com.pce.weatherapp.data.db.entity.CurrentWeather
import com.pce.weatherapp.data.db.entity.WeatherLocation
import com.pce.weatherapp.data.db.unitlocalized.LocalizedCurrentWeather
import com.pce.weatherapp.data.network.WeatherNetworkDataSource
import com.pce.weatherapp.data.network.response.CurrentWeatherResponse
import com.pce.weatherapp.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class WeatherRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource,
    private val locationProvider: LocationProvider
) : WeatherRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever {
            persistCurrentWeather(it)
        }
    }

    override suspend fun getCurrentWeather(): LiveData<LocalizedCurrentWeather> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext currentWeatherDao.getCurrentWeather()
        }
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getLocation()
        }
    }

    private fun persistCurrentWeather(fetchedCurrentWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(
                CurrentWeather(
                    current = fetchedCurrentWeather.current,
                    location = fetchedCurrentWeather.location
                )
            )
            weatherLocationDao.upsert(fetchedCurrentWeather.location)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun initWeatherData() {

        val lastWeatherLocation = weatherLocationDao.getLocationNonLive()
        if (lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
        } else if (isFetchNeeded(ZonedDateTime.now().minusHours(1))) {
            fetchCurrentWeather()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFetchNeeded(lastFetchedTime: ZonedDateTime): Boolean {
        return lastFetchedTime.isBefore(ZonedDateTime.now().minusMinutes(30))
    }

    private suspend fun fetchCurrentWeather() {
        val location = locationProvider.getPreferredLocationString()
        weatherNetworkDataSource.fetchCurrentWeather(location)
    }
}