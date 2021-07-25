package com.example.weather

import android.app.Application
import androidx.room.Room
import com.example.weatherapi.Repository.LocalRep.FavoriteDB
import com.example.weatherapi.Repository.LocalRep.FavoriteDao

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var db: FavoriteDB? = null
        private const val DB_NAME = "Favorite.db"

        fun getFavoritesDao(): FavoriteDao {
            if (db == null) {
                synchronized(FavoriteDB::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            FavoriteDB::class.java,
                            DB_NAME
                        )
                            .build()
                    }
                }
            }
            return db!!.favoriteDao()
        }
    }
}