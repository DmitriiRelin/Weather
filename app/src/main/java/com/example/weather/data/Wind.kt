package com.example.weatherapi.Data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Wind(
    @SerializedName("speed")
    @Expose
    var speed: Double = 0.0,

    @SerializedName("deg")
    @Expose
    var deg: Int = 0
) : Parcelable