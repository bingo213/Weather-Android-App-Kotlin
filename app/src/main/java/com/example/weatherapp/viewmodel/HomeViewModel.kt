package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.model.Weather
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel(){
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _weather = MutableLiveData<Weather>()
    val weather:LiveData<Weather>
        get() = _weather

    init {
        getWeather()
    }

    private fun getWeather(){
        viewModelScope.launch {
            try {
                var result = WeatherApi.retrofitService.getCurrentWeatherData("Hanoi", "647c123aea55cc2ed476f4d58daf9f8d")
                if (result != null){
                    _weather.value = result
                    Log.i("VM", "weather: ${result.toString()}")
                }

                _response.value = "Success!!!"
            }catch (e: Exception){
                _response.value = "Failure: ${e.message}"
            }
        }
//        WeatherApi.retrofitService.getCurrentWeatherData("Hanoi", "647c123aea55cc2ed476f4d58daf9f8d")

    }
}

