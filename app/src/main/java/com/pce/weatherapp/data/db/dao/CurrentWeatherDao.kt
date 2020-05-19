package com.pce.weatherapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pce.weatherapp.data.db.entity.CURRENT_WEATHER_ID
import com.pce.weatherapp.data.db.entity.CurrentWeather
import com.pce.weatherapp.data.db.unitlocalized.LocalizedCurrentWeather

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(currentWeather: CurrentWeather)

    @Query("SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getCurrentWeather(): LiveData<LocalizedCurrentWeather>
}