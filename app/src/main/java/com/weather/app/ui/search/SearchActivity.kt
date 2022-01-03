package com.weather.app.ui.search

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.simpleadapter.SimpleAdapter
import com.squareup.picasso.Picasso
import com.weather.app.R
import com.weather.app.databinding.ActivitySearchBinding
import com.weather.app.databinding.SearchItemListBinding
import com.weather.app.restful.models.WeatherNow

import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import android.content.Intent





class SearchActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProviders.of(this).get(SearchViewModel::class.java) }
    var binding: ActivitySearchBinding? = null
    var adapter: SimpleAdapter<WeatherNow>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        adapter =
            SimpleAdapter.with<WeatherNow, SearchItemListBinding>(res = R.layout.search_item_list) { adapterPosition, model, binding ->
                Picasso.get().load("http://openweathermap.org/img/w/${model.weather[0].icon}.png")
                    .placeholder(R.drawable.progress_animation).into(binding.imageView2)
                binding.name.text = model.name
                (model.main.temp.toString() + " " + model.weather[0].description).also {
                    binding.temp.text = it
                }
                binding.cardItem.setOnClickListener {
                    val returnIntent = Intent()
                    returnIntent.putExtra("lat",model.coord.lat)
                    returnIntent.putExtra("lon",model.coord.lon)
                    setResult(Activity.RESULT_OK,returnIntent)
                    finish()
                }
            }

        binding!!.simpleSearchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.searchWeather(p0.toString())
                return false
            }


        })

        binding!!.list.layoutManager = GridLayoutManager(this, 1)
        binding!!.list.adapter = adapter
        viewModel.weatherSearch.observe(this, Observer {
            adapter?.let { view ->
                view.clear()
                it?.let {
                    binding!!.list.visibility = View.VISIBLE
                    binding!!.notlist.visibility = View.GONE
                    view.add(it)
                } ?: run {
                    binding!!.notlist.visibility = View.VISIBLE
                    binding!!.list.visibility = View.GONE
                }
                view.notifyDataSetChanged()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}