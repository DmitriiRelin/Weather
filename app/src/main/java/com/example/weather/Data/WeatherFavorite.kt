package com.example.weatherapi.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherFavorite(

    var temp: Double = 0.0,

    var feelsLike: Double = 0.0,

    var tempMin: Double = 0.0,

    var tempMax: Double = 0.0,

    var pressure: Int = 0,

    var humidity: Int = 0,

    var timezone: Int = 0,

    @PrimaryKey
    var id: Int = 0,

    var name: String? = null,
)