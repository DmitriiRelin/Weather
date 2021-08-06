package com.example.weather.viewmodel

import androidx.lifecycle.*
import com.example.weather.App.Companion.getFavoritesDao
import com.example.weather.ResponseResult
import com.example.weatherapi.Data.CityWeather
import com.example.weatherapi.Data.WeatherFavorite
import com.example.weatherapi.Repository.RemoteDataSource
import com.example.weatherapi.Repository.Repository
import com.example.weatherapi.Repository.RepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository = RepositoryImpl(RemoteDataSource(), getFavoritesDao())
) : ViewModel() {

    private val _responseCityWeatherLiveData = MutableLiveData<ResponseResult<CityWeather>>()
    val responseCityWeatherLiveData: LiveData<ResponseResult<CityWeather>> = _responseCityWeatherLiveData

    val cityWeatherLiveData = _responseCityWeatherLiveData.map{
        if(it is ResponseResult.Success) it.cityWeather else null
    }

    val errorLiveData = _responseCityWeatherLiveData.map{
        if(it is ResponseResult.Error)it.error else null
    }

    val isLoadingLiveData = _responseCityWeatherLiveData.map {
        it is ResponseResult.Loading
    }

    private val _detailInfoVisibilityLiveData = MutableLiveData(false)
    val detailInfoVisibilityLiveData:LiveData<Boolean> = _detailInfoVisibilityLiveData

    fun changeDetailInfoVisibility(){
        _detailInfoVisibilityLiveData.value = !(_detailInfoVisibilityLiveData.value ?: false)
    }


    fun addToDB(){
        val data = _responseCityWeatherLiveData.value
        if(data is ResponseResult.Success) {
            repository.addWeather(convert(data.cityWeather))
        }
    }

    fun loadData(city: String) {
        viewModelScope.launch {
           val res = repository.getCityWeather(city)
            // postValue используется при вызове из друго потока
            _responseCityWeatherLiveData.value = (res.body()?.let { ResponseResult.Success(it) }
                ?: ResponseResult.Error(Throwable("Empty resp")))
        }
    }

    fun convert(city: CityWeather) : WeatherFavorite {
        return WeatherFavorite(city.main.temp, city.main.feelsLike, city.main.tempMin, city.main.tempMax, city.main.pressure, city.main.humidity, city.timezone, city.id, city.name )
    }
}
