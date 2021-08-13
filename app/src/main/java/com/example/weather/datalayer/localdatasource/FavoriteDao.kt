package com.example.weatherapi.Repository.LocalRep

import androidx.room.*
import com.example.weather.datalayer.localdatasource.dto.FavoriteCity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM FavoriteCity")
    suspend fun allWeather(): List<FavoriteCity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: FavoriteCity)

    @Delete
    suspend fun delete(city: FavoriteCity)

}