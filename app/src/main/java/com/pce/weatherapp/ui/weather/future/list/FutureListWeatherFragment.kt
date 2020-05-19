package com.pce.weatherapp.ui.weather.future.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pce.weatherapp.R
import com.pce.weatherapp.base.ScopedFragment
import kotlinx.coroutines.launch

class FutureListWeatherFragment : ScopedFragment() {

    private lateinit var viewModel: FutureListWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FutureListWeatherViewModel::class.java)
        bindView()
    }

    private fun bindView() = launch {
        updateActionBar("London")
    }

    private fun updateActionBar(location: String) {
        with((activity as? AppCompatActivity)?.supportActionBar) {
            this?.title = location
            this?.subtitle = "7 days"
        }
    }

}
