package com.example.weatherapp.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.weatherapp.*
import com.example.weatherapp.api.DailyForecastApi
import com.example.weatherapp.api.WeatherApi
import com.example.weatherapp.api.WeatherForecastApi
import com.example.weatherapp.model.DailyForecast
import com.example.weatherapp.model.Weather
import com.example.weatherapp.model.WeatherForecast
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class DetailViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

    private var list: ArrayList<CardDetail> = ArrayList()

    private var _listDailyWeather = MutableLiveData<ArrayList<CardDetail>>()
        .apply { postValue(ArrayList()) }
    val listDailyWeather: LiveData<ArrayList<CardDetail>>
        get() = _listDailyWeather

    init {
        getDailyForecast()
        getWeather()
        Log.i("Detail VM", "init detail VM")
    }

    private fun getDailyForecast() {
        viewModelScope.launch {
            try {
                Log.i("Debug", "Long: ${LONGITUDE}, Lat: ${LATITUDE}")
                val result = DailyForecastApi.dailyForecastService.getDailyForecast(
                    LATITUDE,
                    LONGITUDE,
                    APPID
                )
//                _weatherForecast.value = result
                for (i: Int in 1 until result.daily.size) {
                    val iconUrl = BASE_URL + "/img/w/" + result.daily[i].weather[0].icon + ".png"
                    result.daily[i]?.temp?.max?.minus(KELVIN_TO_CELSIUS)?.roundToInt()
                        ?.let { tempMax ->
                            result.daily[i]?.temp?.min?.minus(KELVIN_TO_CELSIUS)?.roundToInt()
                                ?.let { tempMin ->
                                    CardDetail(tempMax, tempMin, iconUrl, getDateTime(i))
                                }
                        }?.let { _listDailyWeather.value?.add(it) }
                }
                _listDailyWeather.value = _listDailyWeather.value
                _response.value = "Success!!!"
                Log.i("Detail VM", "list size: ${list.size}")
                Log.i("Detail VM", "forecastFiveDay size: ${_listDailyWeather.value?.size}")
                Log.i("Detail VM", _response.value!!)
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.i("Detail VM", e.toString())
            }
        }
    }

    private fun getWeather() {
        viewModelScope.launch {
            try {
                val result =
                    WeatherApi.retrofitService.getCurrentWeatherData(LATITUDE, LONGITUDE, APPID)
                _weather.value = result
                _response.value = "Success!!!"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.i("VM", e.toString())
            }
        }
    }

    private fun getDateTime(i: Int): String {
        var date = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DATE, i)
        date = calendar.time
        return DateFormat.getDateInstance(
            DateFormat.SHORT
        )
            .format(date).toString()
    }

}

