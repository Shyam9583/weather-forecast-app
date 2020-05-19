package com.pce.weatherapp.ui.weather.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pce.weatherapp.R
import com.pce.weatherapp.base.ScopedFragment
import com.pce.weatherapp.internal.glide.GlideApp
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModelFactory by instance<CurrentWeatherViewModelFactory>()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CurrentWeatherViewModel::class.java)
        bindView()
    }

    private fun bindView() = launch {

        viewModel.weatherLocation.await().observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            updateLocation(it.name)
        })

        viewModel.weather.await().observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            group_loading.visibility = View.GONE
            updateTemperature(it.temperature, it.temperature)
            updateCondition(it.weatherDescriptions[0])
            updatePrecipitation(it.precipitation)
            updateWind(it.windDir, it.windSpeed)
            updateVisibility(it.visibility)
            GlideApp.with(this@CurrentWeatherFragment)
                .load(it.weatherIcons[0])
                .into(imageView_condition_icon)
        })
    }

    private fun updateLocation(location: String) {
        with((activity as? AppCompatActivity)?.supportActionBar) {
            this?.title = location
            this?.subtitle = "Today"
        }
    }

    private fun updateTemperature(temperature: Double, feelsLikeTemperature: Double) {
        val abbreviation = "°C"
        textView_temperature.text = "$temperature$abbreviation"
        textView_feels_like_temperature.text = "Feels Like $feelsLikeTemperature$abbreviation"
    }

    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }

    private fun updatePrecipitation(volume: Double) {
        val abbreviation = "mm"
        textView_precipitation.text = "Precipitation: $volume$abbreviation"
    }

    private fun updateWind(windDirection: String, windSpeed: Double) {
        val unitAbbreviation = "kph"
        textView_wind.text = "Wind: $windDirection, $windSpeed $unitAbbreviation"
    }

    private fun updateVisibility(visibilityDistance: Double) {
        val unitAbbreviation = "km"
        textView_visibility.text = "Visibility: $visibilityDistance $unitAbbreviation"
    }

}
