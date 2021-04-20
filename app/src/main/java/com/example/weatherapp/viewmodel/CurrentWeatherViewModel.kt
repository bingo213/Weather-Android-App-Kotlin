package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.APPID
import com.example.weatherapp.LATITUDE
import com.example.weatherapp.LONGITUDE
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.model.Weather
import kotlinx.coroutines.launch

class CurrentWeatherViewModel : ViewModel(){
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
//        _response.value = "Hello"
        viewModelScope.launch {
            try {
                var result = WeatherApi.retrofitService.getCurrentWeatherData(LATITUDE, LONGITUDE, APPID)
                if (result != null){
                    _weather.value = result

                }

                _response.value = "Success!!!"
            }catch (e: Exception){
                _response.value = "Failure: ${e.message}"
                Log.i("VM", e.toString())
            }
        }



    }
}

