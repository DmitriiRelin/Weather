package com.example.weather

import com.example.weatherapi.Data.CityResponse

sealed class AppStateHomeFragment {

    data class Success(val cityResponse: CityResponse) : AppStateHomeFragment()
    data class Error(val error: Throwable) : AppStateHomeFragment()
    data class Loading(val progress: Int?) : AppStateHomeFragment()

}