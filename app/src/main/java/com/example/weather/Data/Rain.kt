package com.example.weatherapi.Data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Rain(
    @SerializedName("1h")
    @Expose
    var _1h: Double = 0.0
) : Parcelable