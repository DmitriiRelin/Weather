package com.example.weatherapi.ViewModel

import androidx.lifecycle.*
import com.example.weather.App
import com.example.weather.ResponseResult
import com.example.weatherapi.Data.WeatherFavorite
import com.example.weatherapi.Repository.RemoteDataSource
import com.example.weatherapi.Repository.Repository
import com.example.weatherapi.Repository.RepositoryImpl
import kotlinx.coroutines.launch
import java.lang.Exception


class FavoritesViewModel(
    private val repository: Repository = RepositoryImpl(RemoteDataSource(), App.getFavoritesDao())
) : ViewModel() {

    private val _favoritesResponseLiveData = MutableLiveData<ResponseResult<List<WeatherFavorite>>>()

    val favoriteWeatherListLiveData = _favoritesResponseLiveData.map{
        if(it is ResponseResult.Success) it.data else null
    }

    val errorLiveData = _favoritesResponseLiveData.map{
        if(it is ResponseResult.Error)it.error else null
    }

    val isLoadingLiveData = _favoritesResponseLiveData.map {
        it is ResponseResult.Loading
    }

    fun getList() {
        viewModelScope.launch {
            _favoritesResponseLiveData.value = ResponseResult.Loading(null)
            try{
            val list = repository.getFavoriteListWeather()
                _favoritesResponseLiveData.value = ResponseResult.Success(list)
            } catch (e: Exception){
                _favoritesResponseLiveData.value = ResponseResult.Error(e)
            }
        }
    }

    fun deleteFavorite(pos: Int) {
        val state = _favoritesResponseLiveData.value
        if(state is ResponseResult.Success){
            repository.deleteFavorite(state.data[pos])
            getList()
        }

    }

}