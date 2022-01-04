package com.weather.app.ui.futureweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.simpleadapter.SimpleAdapter
import com.squareup.picasso.Picasso
import com.weather.app.R
import com.weather.app.core.utils.TransitionsHelper
import com.weather.app.core.utils.UnitSystem
import com.weather.app.databinding.ForecastlistItemBinding
import com.weather.app.databinding.FragmentFutureWeatherBinding
import com.weather.app.restful.models.ForecastList
import com.weather.app.restful.models.WeatherData
import com.weather.app.ui.main.MainActivity
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class FutureWeatherFragment  : Fragment(){

    var binding: FragmentFutureWeatherBinding? = null
    var adapter: SimpleAdapter<WeatherData>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentFutureWeatherBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        TransitionsHelper.setupTransitions(this)


        setUpDataBinding()
        // TODO: Use the ViewModel
    }

    private fun setUpDataBinding(){

        adapter = SimpleAdapter.with<WeatherData, ForecastlistItemBinding>(res = R.layout.forecastlist_item) { adapterPosition, model, binding ->
                Picasso.get().load("http://openweathermap.org/img/w/${model.weather[0].icon}.png")
                    .placeholder(R.drawable.progress_animation).into(binding.weatherIndicatorImage)
            (model.main.temp.roundToInt().toString()+" "+ UnitSystem.Metric.unit).also { binding.temperatureText.text = it }

            (model.main.feels_like.toString() +" "+ model.weather[0].description).also { binding.realFeelText.text = it }


              binding.timeNow.text = model.dt_txt
            }

        binding!!.list.layoutManager = GridLayoutManager(requireContext(), 1)
        binding!!.list.adapter = adapter

        (activity as MainActivity).viewModel.forecastList.value.let {

        }

        (activity as MainActivity).viewModel.forecastList.observe(viewLifecycleOwner, Observer {

            adapter?.let { view ->
                view.clear()
                it?.let {
                    view.addAll(it.list)
                }
                view.notifyDataSetChanged()
            }
        })



    }
}
