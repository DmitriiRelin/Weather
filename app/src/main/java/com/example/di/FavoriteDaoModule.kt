package com.example.di

import androidx.room.Room
import com.example.weather.App
import com.example.weatherapi.Repository.LocalRep.FavoriteDB
import com.example.weatherapi.Repository.LocalRep.FavoriteDao
import dagger.Module
import dagger.Provides

@Module
object FavoriteDaoModule {
    @Provides
    fun provideFavoriteDao(): FavoriteDao {
        val db = Room.databaseBuilder(
            App.appInstance!!.applicationContext,
            FavoriteDB::class.java,
            App.DB_NAME
        )
            .build()
        return db.favoriteDao()
    }


}