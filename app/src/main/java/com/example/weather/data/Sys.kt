package com.example.weatherapi.Data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("type")
    @Expose
    var type: Int = 0,

    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("country")
    @Expose
    var country: String? = null,

    @SerializedName("sunrise")
    @Expose
    var sunrise: Int = 0,

    @SerializedName("sunset")
    @Expose
    var sunset: Int = 0
)