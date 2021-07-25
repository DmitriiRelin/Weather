package com.example.weatherapi.View.RecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.databinding.FavoriteItemBinding
import com.example.weatherapi.Data.WeatherFavorite

class FavoritesHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding: FavoriteItemBinding = FavoriteItemBinding.bind(itemView)

    fun onBind(cityResponse: WeatherFavorite) {
        binding.tvTitle.text = "CITY: ${cityResponse.name}"
        binding.tvTemp.text = "TEMP: ${cityResponse.temp}"
        binding.tempMin.text = "TEMP MIN: ${cityResponse.tempMin}"
        binding.tempMax.text = "TEMP MAX: ${cityResponse.tempMax}"
    }

}