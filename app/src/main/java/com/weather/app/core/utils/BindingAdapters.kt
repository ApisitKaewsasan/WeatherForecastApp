package com.weather.app.core.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.weather.app.R
import com.weather.app.restful.models.WeatherNow
import kotlin.math.roundToInt


@BindingAdapter("goneIfNull")
fun goneIfNull(view: View, it: Any?) {
    view.visibility = if (it == null) View.GONE else View.VISIBLE
}

@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}



@BindingAdapter("convertDoubleToString")
fun convertDoubleToString(view: TextView, it: Any?) {
    view.text = it.toString()
}

@BindingAdapter("convertTemp")
fun convertTemp(view: TextView, it: Double?) {
    ((it!!).roundToInt().toString()+" "+UnitSystem.Metric.unit).also { view.text = it }
}



@BindingAdapter("weatherDescriptionText")
fun weatherDescriptionText(view: TextView, it: WeatherNow?) {

    it?.let {
        "${it.main.temp} ${it.weather[0].description}".also { view.text = it }
    } ?: run {
        view.text = "no"
    }


}

@BindingAdapter("weatherIcon")
fun weatherIcon(view: ImageView, image: WeatherNow?) {
    image?.let {

      //  Picasso.get().load("http://openweathermap.org/img/w/${image.weather[0].icon}.png").placeholder(R.drawable.progress_animation).into(view)
    }?: run {
      //  Picasso.get().load(R.drawable.ic_cloud_queue_dark).error(R.drawable.ic_cloud_queue_dark).placeholder(R.drawable.ic_cloud_queue_dark).into(view)
    }
}



//http://openweathermap.org/img/w/02d.png



