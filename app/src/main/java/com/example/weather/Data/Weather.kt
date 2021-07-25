package com.example.weatherapi.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Weather(
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("main")
    @Expose
    var main: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("icon")
    @Expose
    var icon: String? = null
)