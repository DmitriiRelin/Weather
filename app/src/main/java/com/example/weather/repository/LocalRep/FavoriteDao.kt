package com.example.weatherapi.Repository.LocalRep

import androidx.room.*
import com.example.weatherapi.Data.WeatherFavorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM WeatherFavorite")
    suspend fun allWeather(): List<WeatherFavorite>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: WeatherFavorite)

    @Delete
    suspend fun delete(city: WeatherFavorite)

}