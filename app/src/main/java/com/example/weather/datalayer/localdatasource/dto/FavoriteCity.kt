package com.example.weather.datalayer.localdatasource.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCity (
    @PrimaryKey
    val cityName: String
)