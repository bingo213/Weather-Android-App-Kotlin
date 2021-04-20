package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.*
import com.example.weatherapp.api.WeatherForecastApi
import com.example.weatherapp.model.WeatherForecast
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class WeatherForecastViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _weatherForecast = MutableLiveData<WeatherForecast>()
    val weatherForecast: LiveData<WeatherForecast>
        get() = _weatherForecast

    private var _listDayWeather = MutableLiveData<ArrayList<DayWeather>>()
        .apply { postValue(ArrayList()) }
    val listDayWeather: LiveData<ArrayList<DayWeather>>
        get() = _listDayWeather

    init {
        getWeatherForecast()
    }

    private fun getWeatherForecast() {
        viewModelScope.launch {
            try {
                Log.i("Debug", "Long: ${LONGITUDE}, Lat: ${LATITUDE}")
                var result = WeatherForecastApi.weatherForecastService.getWeatherForecast(
                    LATITUDE,
                    LONGITUDE,
                    APPID
                )
                _weatherForecast.value = result
                for (i in 0..HOUR_FORECAST.size - 1) {
                    val iconUrl = BASE_URL + "/img/w/" + result.list[i].weather[0].icon + ".png"
                    result.list[i].main?.temp?.minus(KELVIN_TO_CELSIUS)?.roundToInt()?.let {
                        DayWeather(HOUR_FORECAST[i].toString() + "h", it, iconUrl)
                    }?.let { _listDayWeather.value?.add(it) }
                }

                _response.value = "Success!!!"
                _listDayWeather.value = _listDayWeather.value
                Log.i("VM", _response.value!!)
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.i("VM", e.toString())
            }
        }

    }
}

