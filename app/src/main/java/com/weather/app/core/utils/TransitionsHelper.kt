package com.weather.app.core.utils

import androidx.fragment.app.Fragment
import com.weather.app.core.utils.transitions.FadeScale

object TransitionsHelper {
    fun setupTransitions(fragment: Fragment) {
        val transitionDuration = 300L
        val animation = FadeScale().apply {
            duration = transitionDuration
        }
        fragment.enterTransition = animation
        fragment.exitTransition = animation
        fragment.returnTransition = animation
    }
}