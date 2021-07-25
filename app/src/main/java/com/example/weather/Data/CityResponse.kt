package com.example.weatherapi.Data

import android.os.Parcelable
import androidx.room.Ignore
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityResponse(

    @SerializedName("main")
    @Expose
    var main: Main,

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null,

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null,

    @SerializedName("timezone")
    @Expose
    var timezone: Int = 0,


    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    ) : Parcelable {

    @Ignore
    @SerializedName("coord")
    @Expose
    var coord: Coord? = null

    @Ignore
    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null

    @Ignore
    @SerializedName("base")
    @Expose
    var base: String? = null

    @Ignore
    @SerializedName("visibility")
    @Expose
    var visibility: Int = 0

    @Ignore
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @Ignore
    @SerializedName("dt")
    @Expose
    var dt: Int = 0

    @Ignore
    @SerializedName("sys")
    @Expose
    var sys: Sys? = null

    @Ignore
    @SerializedName("cod")
    @Expose
    var cod: Int = 0

}