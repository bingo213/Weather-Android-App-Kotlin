package com.example.weatherapp.api

import com.example.weatherapp.model.Weather
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val BASE_URL = "http://api.openweathermap.org"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WeatherService {
    @GET("data/2.5/weather?")
    suspend fun getCurrentWeatherData(
//        @Query("lat") lat: String,
//        @Query("lon") lon: String,
        @Query("q") city: String,
        @Query("appid") app_id: String
    ) : Weather
}

object WeatherApi{
    val retrofitService : WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }
}

//interface IconService{
//    @GET("img/wn/{iconId}.png")
//    suspend fun getWeatherItem(@Path("iconId") icon: String) :
//}
//
//object WeatherIconApi{
//    val iconService : IconService by lazy {
//        retrofit.create(IconService::class.java)
//    }
//}

