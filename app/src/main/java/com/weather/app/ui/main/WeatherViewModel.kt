package com.weather.app.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.app.core.utils.UnitSystem
import com.weather.app.restful.ApiUtil
import com.weather.app.restful.models.ForecastList
import com.weather.app.restful.models.WeatherNow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    var weatherNow: MutableLiveData<WeatherNow> = MutableLiveData()
    var forecastList: MutableLiveData<ForecastList> = MutableLiveData()

    fun getWeatherNow(lat:Double,long: Double){

        ApiUtil.weartherServicer().weatherNow(lat,long, UnitSystem.Metric.toString()).enqueue(object :
            Callback<WeatherNow> {
            override fun onResponse(call: Call<WeatherNow>, response: Response<WeatherNow>) {
                if(response.code()==200){
                    response.body().run {
                        forecastList(this!!.name)
                    }
                    weatherNow.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<WeatherNow>, t: Throwable) {
                weatherNow.postValue(null)
            }

        })
    }


    fun forecastList(query:String){
        ApiUtil.weartherServicer().forecastList(query,UnitSystem.Metric.toString()).enqueue(object :
            Callback<ForecastList> {
            override fun onResponse(call: Call<ForecastList>, response: Response<ForecastList>) {
                if(response.code()==200){
                    forecastList.postValue(response.body())
                }else{
                    forecastList.postValue(null)
                }
            }
            override fun onFailure(call: Call<ForecastList>, t: Throwable) {
                forecastList.postValue(null)
            }
        })
    }
}