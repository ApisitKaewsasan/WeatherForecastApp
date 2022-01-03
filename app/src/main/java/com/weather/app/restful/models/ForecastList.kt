package com.weather.app.restful.models

data class ForecastList(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherData>,
    val message: Int
)