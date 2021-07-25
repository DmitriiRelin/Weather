package com.example.weatherapi.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.App
import com.example.weather.AppStateFavoritesFragment
import com.example.weatherapi.Repository.RemoteDataSource
import com.example.weatherapi.Repository.Repository
import com.example.weatherapi.Repository.RepositoryImpl
import kotlinx.coroutines.launch


class FavoritesViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppStateFavoritesFragment> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource(), App.getFavoritesDao())
) : ViewModel() {

    fun getData(): LiveData<AppStateFavoritesFragment> {
        getList()
        return liveDataForViewToObserve
    }

    fun getList() {
        viewModelScope.launch {
            val list = repository.getListWeather()
            liveDataForViewToObserve.postValue(AppStateFavoritesFragment.Success(list))
        }
    }

    fun deleteFavorite(pos: Int) {
        val state = liveDataForViewToObserve.value
        if(state is AppStateFavoritesFragment.Success){
            repository.deleteFavorite(state.cityResponse[pos])
            getList()
        }

    }

}