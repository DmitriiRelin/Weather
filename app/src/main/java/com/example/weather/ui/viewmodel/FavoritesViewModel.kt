package com.example.weatherapi.ViewModel

import androidx.lifecycle.*
import com.example.weather.di.RoomImpl
import com.example.weather.utils.ResponseResult
import com.example.weatherapi.Data.WeatherFavorite
import com.example.weatherapi.Repository.LocalRep.FavoriteDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    @RoomImpl val favoriteDao: FavoriteDao
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
            val list = favoriteDao.allWeather()
                _favoritesResponseLiveData.value = ResponseResult.Success(list)
            } catch (e: Exception){
                _favoritesResponseLiveData.value = ResponseResult.Error(e)
            }
        }
    }

    fun deleteFavorite(pos: Int) {
        val state = _favoritesResponseLiveData.value
        if(state is ResponseResult.Success){
            viewModelScope.launch {
                favoriteDao.delete(state.data[pos])
                getList()
            }
        }
    }
}