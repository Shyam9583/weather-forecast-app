package com.pce.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime


const val WEATHER_LOCATION_ID = 0

@Entity(tableName = "weather_location")
data class WeatherLocation(
    val country: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("timezone_id")
    val zoneId: String,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Long,
    @SerializedName("lon")
    val longitude: Double,
    val name: String
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = WEATHER_LOCATION_ID

    val zonedDateTime: ZonedDateTime
        get() = ZonedDateTime.ofInstant(Instant.ofEpochSecond(localtimeEpoch), ZoneId.of(zoneId))
}