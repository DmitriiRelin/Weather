package com.example.weather.datalayer.repository

import com.example.weather.domain.entities.City
import javax.inject.Inject

class FavoritesRepository @Inject constructor() {
    fun addToFavorite(city: City){

    }
    fun removeFromFavorite(city: City){

    }

    fun getFavoriteList(): List<City>{
        TODO()
    }
}