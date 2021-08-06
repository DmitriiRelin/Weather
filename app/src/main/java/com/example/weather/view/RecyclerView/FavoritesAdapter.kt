package com.example.weatherapi.View.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weatherapi.Data.WeatherFavorite


class FavoritesAdapter() : RecyclerView.Adapter<FavoritesHolder>() {

    var favoritesList = listOf<WeatherFavorite>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        return FavoritesHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        holder.onBind(favoritesList[position])
    }

    override fun getItemCount() = favoritesList.size
}