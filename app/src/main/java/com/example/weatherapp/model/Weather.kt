package com.example.weatherapp.model

class Weather {
    var weather = ArrayList<WeatherMain>()
    var main: Main? = null
    var wind: Wind? = null
    var clouds: Clouds? = null
    var name: String? = null
}

class Wind {
    var speed = 0f
}

class Clouds {
    var all = 0f
}

class Main {
    var temp = 0f
    var feels_like = 0f
    var humidity = 0f
}

class WeatherMain {
    var main: String? = null
}