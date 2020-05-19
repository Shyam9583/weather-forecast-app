package com.pce.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pce.weatherapp.data.db.dao.CurrentWeatherDao
import com.pce.weatherapp.data.db.dao.WeatherLocationDao
import com.pce.weatherapp.data.db.entity.CurrentWeather
import com.pce.weatherapp.data.db.entity.WeatherLocation
import com.pce.weatherapp.internal.ArrayStringTypeConverter

@Database(entities = [CurrentWeather::class, WeatherLocation::class], version = 1)
@TypeConverters(ArrayStringTypeConverter::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract fun weatherLocationDao(): WeatherLocationDao

    companion object {
        @Volatile
        private var instance: ForecastDatabase? = null
        private val LOCK = Any()
        private const val DATABASE_NAME = "weather_forecast_database"
        operator fun invoke(context: Context): ForecastDatabase = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ForecastDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}