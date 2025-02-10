package com.emanuel.weatherapp.presentation.details

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.emanuel.weatherapp.R
import com.emanuel.weatherapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            println("ARGS: ${args.weatherInfo?.cityName}")
            args.weatherInfo?.let {
                cityNameTxt.text = it.cityName
                dayOfWeekTxt.text = it.dayOfWeek
                dayOfMonthTxt.text = it.currentDate
                temperatureTxt.text = getString(R.string.temperature_text, it.temperature)
                climateImg.setImageResource(getWeatherIcon(it.icon))
                climateTxt.text = it.climate
                humidityTxt.text = getString(R.string.humidity_text, it.humidity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.statusBarColor = resources.getColor(R.color.blue_sky)
    }

    private fun getWeatherIcon(iconCode: String): Int {
        return when(iconCode) {
            "01d" -> R.drawable.weather_01d
            "01n" -> R.drawable.weather_01n
            "02d" -> R.drawable.weather_02d
            "02n" -> R.drawable.weather_02n
            "03d" -> R.drawable.weather_03d
            "03n" -> R.drawable.weather_03n
            "04d" -> R.drawable.weather_04d
            "04n" -> R.drawable.weather_04n
            "09d" -> R.drawable.weather_09d
            "09n" -> R.drawable.weather_09n
            "10d" -> R.drawable.weather_10d
            "10n" -> R.drawable.weather_10n
            "11d" -> R.drawable.weather_11d
            "11n" -> R.drawable.weather_11n
            "13d" -> R.drawable.weather_13d
            "13n" -> R.drawable.weather_13n
            "50d" -> R.drawable.weather_50d
            else -> R.drawable.weather_50n
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}