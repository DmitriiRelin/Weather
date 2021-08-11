package com.example.weatherapi.Repository

import com.example.weatherapi.Data.CityWeather
import retrofit2.Response

interface IRemoteDataSource {
    suspend fun getCityWeather(city: String): Response<CityWeather>
}