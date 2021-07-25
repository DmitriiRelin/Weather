package com.example.weatherapi.Repository

import com.example.weatherapi.Data.CityResponse
import com.example.weatherapi.Data.WeatherFavorite
import retrofit2.Response

interface Repository {

    //fun getCityWeather(city: String): Single<CityResponse>

    suspend fun getCityWeather(city: String): Response<CityResponse>

    fun addWeather(weatherFavorite: WeatherFavorite)

    suspend fun getListWeather(): List<WeatherFavorite>

    fun deleteFavorite(city: WeatherFavorite)

}