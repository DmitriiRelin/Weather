package com.example.weatherapi.Repository.LocalRep

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weather.datalayer.localdatasource.dto.FavoriteCity

@Database(entities = [FavoriteCity::class], version = 1, exportSchema = false)
abstract class FavoriteDB : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao
}