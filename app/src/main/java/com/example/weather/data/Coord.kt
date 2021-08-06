package com.example.weatherapi.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Coord(
    @SerializedName("lon")
    @Expose
    var lon: Double = 0.0,

    @SerializedName("lat")
    @Expose
    var lat: Double = 0.0
)