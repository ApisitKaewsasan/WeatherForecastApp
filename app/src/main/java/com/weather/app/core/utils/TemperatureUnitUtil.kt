package com.weather.app.core.utils

import java.util.Locale

fun Locale.getLocaleTemperatureUnit() : UnitSystem {
    return when (this) {
        Locale.US -> UnitSystem.Imperial
        else -> UnitSystem.Metric
    }
}

enum class UnitSystem(val unit: String) {
    Standard("°K"),
    Metric("°C"),
    Imperial("°F")
}