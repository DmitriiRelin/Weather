package com.example.weatherapi.Repository

import com.example.weatherapi.Data.CityWeather
import com.example.weatherapi.Data.WeatherFavorite
import retrofit2.Response

interface Repository {

    //fun getCityWeather(city: String): Single<CityResponse>

    suspend fun getCityWeather(city: String): Response<CityWeather>

    fun addWeather(weatherFavorite: WeatherFavorite)

    suspend fun getListWeather(): List<WeatherFavorite>

    fun deleteFavorite(city: WeatherFavorite)

}