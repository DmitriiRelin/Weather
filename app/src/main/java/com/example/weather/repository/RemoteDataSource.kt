package com.example.weatherapi.Repository

import com.example.weather.api.WeatherApi
import com.example.weather.BuildConfig
import com.example.weatherapi.Data.CityWeather
import com.example.weatherapi.Utils.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

class Some @Inject constructor(){

}

@Singleton
class RemoteDataSource @Inject constructor(val some: Some) {
    private val weatherApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().setLenient().create())
        )
        .client(createOkHttpClient(PODInterceptor()))
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherApi::class.java)


    //fun getCityWeather(city: String) = weatherApi.getCityWeather(city, BuildConfig.WEATHER_API_KEY)
    suspend fun getCityWeather(city: String) =
        weatherApi.getCityWeather(city, BuildConfig.WEATHER_API_KEY)


    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    inner class PODInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }

}