package com.weather.app.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weather.app.core.utils.UnitSystem
import com.weather.app.core.utils.getLocaleTemperatureUnit
import com.weather.app.restful.ApiUtil
import com.weather.app.restful.models.WeatherNow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchViewModel : ViewModel() {
    var weatherSearch: MutableLiveData<WeatherNow> = MutableLiveData()
    fun searchWeather(query:String){

        ApiUtil.weartherServicer().weatherSearch(query, UnitSystem.Metric.toString()).enqueue(object :
            Callback<WeatherNow> {
            override fun onResponse(call: Call<WeatherNow>, response: Response<WeatherNow>) {
                if(response.code()==200){
                    weatherSearch.postValue(response.body())
                }else{
                    weatherSearch.postValue(null)
                }

            }
            override fun onFailure(call: Call<WeatherNow>, t: Throwable) {
                weatherSearch.postValue(null)
            }
        })
    }
}