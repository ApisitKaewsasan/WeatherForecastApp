package com.weather.app.restful

import com.google.gson.GsonBuilder
import com.weather.app.restful.RetrofitService.WeatherServiceInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiUtil {
    companion object{
        private const val API_URL = "https://api.openweathermap.org/data/2.5/"

        fun weartherServicer(): WeatherServiceInterface {
            return getRetrofit(API_URL).create(WeatherServiceInterface::class.java)
        }

        private fun getRetrofit(url: String): Retrofit {
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()

                .baseUrl(url).client(httpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        }

        private fun httpClient(): OkHttpClient {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }

    }
}