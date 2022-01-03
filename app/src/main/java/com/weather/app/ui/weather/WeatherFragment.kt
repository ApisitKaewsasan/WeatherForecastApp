package com.weather.app.ui.weather

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.weather.app.core.utils.TransitionsHelper
import com.weather.app.databinding.FragmentWeatherBinding
import com.weather.app.ui.search.SearchActivity
import android.app.Activity
import com.weather.app.ui.main.MainActivity


class WeatherFragment  : Fragment(){


    var binding: FragmentWeatherBinding? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentWeatherBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        TransitionsHelper.setupTransitions(this)

        setUpDataBinding()
        // TODO: Use the ViewModel
    }



    private fun setUpDataBinding(){
        (activity as MainActivity).viewModel.weatherNow.observe(viewLifecycleOwner, Observer {
           // Toast.makeText(requireActivity(), "data "+it, Toast.LENGTH_SHORT).show()
            binding!!.data = it
        })
        binding!!.iconSearch.setOnClickListener {
           startActivityForResult(Intent(requireActivity(),SearchActivity::class.java),1)
        }
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activity
        when {
            requestCode == 1 && resultCode == Activity.RESULT_OK -> {
                val lat = data!!.getDoubleExtra("lat",0.0)
                val lon = data.getDoubleExtra("lon",0.0)
                (activity as MainActivity).viewModel.getWeatherNow(lat,lon)
            }
        }
    }
}