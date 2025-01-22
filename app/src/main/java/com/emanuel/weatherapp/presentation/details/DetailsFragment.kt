package com.emanuel.weatherapp.presentation.details

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
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
                temperatureTxt.text = "${it.temperature}ºc"
                climateTxt.text = it.climate
                humidityTxt.text = "${it.humidity}%"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}