package com.example.weatherapi.Repository

import com.example.weatherapi.Data.CityWeather
import com.example.weatherapi.Data.WeatherFavorite
import com.example.weatherapi.Repository.LocalRep.FavoriteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: FavoriteDao
) : Repository {

    override suspend fun getCityWeather(city: String): Response<CityWeather> =
        remoteDataSource.getCityWeather(city)

    override fun addWeather(weatherFavorite: WeatherFavorite) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.insert(weatherFavorite)
        }
    }

    override suspend fun getListWeather(): List<WeatherFavorite> {
        return localDataSource.allWeather()
    }

    override fun deleteFavorite(city: WeatherFavorite) {
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.delete(city)
        }
    }


    //override fun getCityWeather(city: String): Single<CityResponse> = remoteDataSource.getCityWeather(city)

}