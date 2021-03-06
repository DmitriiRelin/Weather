package com.example.weather.api

import com.example.weatherapi.Data.CityWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    //RX
//    @GET("data/2.5/weather")
//    fun getCityWeather(
//        @Query("q") q: String,
//        @Query("appid") appid: String
//    ): Single<CityResponse>

    @GET("data/2.5/weather")
    suspend fun getCityWeather(
        @Query("q") q: String,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric"
    ): Response<CityWeather>
}
