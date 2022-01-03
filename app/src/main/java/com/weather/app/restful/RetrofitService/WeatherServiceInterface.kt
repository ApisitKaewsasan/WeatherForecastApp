package com.weather.app.restful.RetrofitService

import com.weather.app.restful.models.ForecastList
import com.weather.app.restful.models.WeatherNow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServiceInterface {
    @GET("weather?appid=485c62567ce6f2e1bc90b886d782f904&lang=en")
    fun weatherNow(@Query("lat") lat: Double,@Query("lon") lon: Double,@Query("units") units:String): Call<WeatherNow>

    @GET("weather?appid=485c62567ce6f2e1bc90b886d782f904&lang=en")
    fun weatherSearch(@Query("q") p: String,@Query("units") units:String): Call<WeatherNow>

    @GET("forecast?appid=485c62567ce6f2e1bc90b886d782f904&lang=en")
    fun forecastList(@Query("q") p: String,@Query("units") units:String): Call<ForecastList>
}