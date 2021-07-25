package com.example.weather

import com.example.weatherapi.Data.WeatherFavorite

sealed class AppStateFavoritesFragment {

    data class Success(val cityResponse: List<WeatherFavorite>) : AppStateFavoritesFragment()
    data class Error(val error: Throwable) : AppStateFavoritesFragment()
    data class Loading(val progress: Int?) : AppStateFavoritesFragment()

}