package com.example.weather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application()


//
//первый экран:
//1. получать погоду в городе по его названию
//2. добавлять в избранное
//3. удалять из избранного
//4. желтая или серая зведочка (если избранный или нет)
//
//второй экран:
//1. список избранных городов с текущей погодой
//2. удалять из избранного
//
//-у города нужны: название, координаты (потом хочу карту), погода
//-погода это температура, влажность, давление
//-в избранном максимум пять городов
//-в будущем иметь возможность получать погоду с разных api

//UseCases
//GetCityByName
//AddCityToFavorite(max 5 city)
//RemoveFromFavorite
//GetCitiesFromFavorite
//Entities








