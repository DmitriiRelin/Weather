package com.example.weather.viewmodel

import androidx.lifecycle.*
import com.example.weather.App
import com.example.weather.ResponseResult
import com.example.weatherapi.Data.CityWeather
import com.example.weatherapi.Data.WeatherFavorite
import com.example.weatherapi.Repository.IRemoteDataSource
import com.example.weatherapi.Repository.LocalRep.FavoriteDao
import com.example.weatherapi.Repository.RemoteDataSource
import kotlinx.coroutines.launch

class HomeViewModel(
    val remoteDataSource: IRemoteDataSource,
    val dao:FavoriteDao
) : ViewModel() {

    private val _responseCityWeatherLiveData = MutableLiveData<ResponseResult<CityWeather>>()

    val cityWeatherLiveData = _responseCityWeatherLiveData.map{
        if(it is ResponseResult.Success) it.data else null
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

    val userInputLiveData = MutableLiveData("")

    private val _inputError = MutableLiveData("")
    val inputError: LiveData<String> = _inputError

    init {
        userInputLiveData.observeForever{userInput->
            if(userInput.isNotEmpty())
                _inputError.value = ""
        }
    }

    fun addToDB(){
        val data = _responseCityWeatherLiveData.value
        if(data is ResponseResult.Success) {
            viewModelScope.launch {
                dao.insert(convert(data.data))
            }

        }
    }

    fun loadData() {
        if (validateUserInput()) {
            viewModelScope.launch {
                _responseCityWeatherLiveData.value = ResponseResult.Loading(null)
                val res = remoteDataSource.getCityWeather(userInputLiveData.value?:"")
                // postValue используется при вызове из друго потока
                _responseCityWeatherLiveData.value = (res.body()?.let { ResponseResult.Success(it) }
                    ?: ResponseResult.Error(Throwable("Empty resp")))
            }
        }
    }

    private fun convert(city: CityWeather) : WeatherFavorite {
        return WeatherFavorite(city.main.temp, city.main.feelsLike, city.main.tempMin, city.main.tempMax, city.main.pressure, city.main.humidity, city.timezone, city.id, city.name )
    }

    private fun validateUserInput(): Boolean{
        val userInput = userInputLiveData.value ?: ""
        return if(userInput.isNotEmpty())
            true
        else{
            _inputError.value = "Empty"
            false
        }
    }

}
