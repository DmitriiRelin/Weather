package com.example.weatherapi.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.App.Companion.getFavoritesDao
import com.example.weather.AppStateHomeFragment
import com.example.weatherapi.Data.CityResponse
import com.example.weatherapi.Data.WeatherFavorite
import com.example.weatherapi.Repository.RemoteDataSource
import com.example.weatherapi.Repository.Repository
import com.example.weatherapi.Repository.RepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppStateHomeFragment> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource(), getFavoritesDao())
) : ViewModel() {



    fun getData(): LiveData<AppStateHomeFragment> {
        return liveDataForViewToObserve
    }

//    fun loadData(city: String) {
//        repository.getCityWeather(city)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                liveDataForViewToObserve.postValue(AppStateHomeFragment.Success(it))
//            }, {
//
//            })
//    }

    fun addToDB(){
        val data = getData().value
        if(data is AppStateHomeFragment.Success) {
            repository.addWeather(convert(data.cityResponse))
        }

    }

    fun loadData(city: String) {
        viewModelScope.launch {
           val res = repository.getCityWeather(city)
            liveDataForViewToObserve.postValue(res.body()?.let { AppStateHomeFragment.Success(it) } ?: AppStateHomeFragment.Error(Throwable("Empty resp")))
        }
    }

    fun convert(city: CityResponse) : WeatherFavorite {
        return WeatherFavorite(city.main.temp, city.main.feelsLike, city.main.tempMin, city.main.tempMax, city.main.pressure, city.main.humidity, city.timezone, city.id, city.name )
    }





}