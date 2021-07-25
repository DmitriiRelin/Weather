package com.example.weatherapi

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replace(contId: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(contId, fragment)
        .addToBackStack(fragment.javaClass::class.java.simpleName)
        .commitAllowingStateLoss()
}