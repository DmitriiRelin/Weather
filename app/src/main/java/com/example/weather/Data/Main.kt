package com.example.weatherapi.Data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Main(

    @SerializedName("temp")
    @Expose
    var temp: Double = 0.0,

    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double = 0.0,

    @SerializedName("temp_min")
    @Expose
    var tempMin: Double = 0.0,

    @SerializedName("temp_max")
    @Expose
    var tempMax: Double = 0.0,

    @SerializedName("pressure")
    @Expose
    var pressure: Int = 0,

    @SerializedName("humidity")
    @Expose
    var humidity: Int = 0
) : Parcelable