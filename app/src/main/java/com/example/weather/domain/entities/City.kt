package com.example.weather.domain.entities


data class City(
    val name: String,
    val coord: Coord,
    val weather: Weather,
    val isInFavorite: Boolean
)