package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.weather.App
import com.example.weatherapi.Repository.LocalRep.FakeFavoriteDao
import com.example.weatherapi.Repository.LocalRep.FavoriteDB
import com.example.weatherapi.Repository.LocalRep.FavoriteDao
import com.example.weatherapi.Repository.Some
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RoomImpl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FakeImpl

@InstallIn(SingletonComponent::class)
@Module
object FavoriteDaoModule {
    private const val DB_NAME = "Favorite.db"

    @RoomImpl
    @Singleton
    @Provides
    fun provideFavoriteDao(@ApplicationContext context: Context): FavoriteDao {
        val db = Room.databaseBuilder(
            context,
            FavoriteDB::class.java,
            DB_NAME
        )
            .build()
        return db.favoriteDao()
    }

    @FakeImpl
    @Singleton
    @Provides
    fun provideFakeFavoriteDao(): FavoriteDao {
        return FakeFavoriteDao()
    }
}