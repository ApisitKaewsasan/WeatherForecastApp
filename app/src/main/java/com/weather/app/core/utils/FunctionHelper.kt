package com.weather.app.core.utils

import java.text.SimpleDateFormat
import java.util.*

object FunctionHelper {
    fun  getTimeNow(): String {
//        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
//        val currentDate = sdf.format(Date())
        return Date().toString()
    }

}