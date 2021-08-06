package com.example.weather.view

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.weatherapi.Data.CityWeather

@BindingAdapter("app:goneIfNull")
fun View.goneIfNull (value: Any?){
    visibility= if(value != null)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("app:visibleIfTrue")
fun View.visibleIfTrue (value: Boolean){
    visibility= if(value)
        View.VISIBLE
    else
        View.GONE
}