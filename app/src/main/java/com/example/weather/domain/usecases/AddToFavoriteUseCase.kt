package com.example.weather.domain.usecases

import com.example.weather.domain.entities.City
import com.example.weather.datalayer.repository.FavoritesRepository
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    val favoritesRepository: FavoritesRepository
) {
    companion object{
        const val MAX_CITY_IN_FAVORITE_COUNT = 5
    }
    suspend operator fun invoke(city: City): Boolean{
        val favoritesCount = favoritesRepository.getFavoriteList().size
        return if(favoritesCount<5){
            favoritesRepository.addToFavorite(city)
            true
        }else
            false
    }
}