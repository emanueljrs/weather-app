package com.emanuel.weatherapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emanuel.weatherapp.R
import com.emanuel.weatherapp.databinding.FragmentHomeBinding
import com.emanuel.weatherapp.domain.model.WeatherInfo
import com.emanuel.weatherapp.presentation.utils.FragmentExt.hideKeyboard
import com.emanuel.weatherapp.presentation.utils.FragmentExt.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()
    private var weatherInfoArgs: WeatherInfo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        setListeners()
    }

    private fun observers() {
        homeViewModel.apply {
            isConnected.observe(viewLifecycleOwner) { isConnected ->
                binding.apply {
                    isConnected?.let {
                        if (!isConnected) {
                            homeContainer.visibility = View.GONE
                            homeNoInternetContainer.visibility = View.VISIBLE
                            changeStatusBarColor(change = true)
                        } else {
                            homeContainer.visibility = View.VISIBLE
                            homeNoInternetContainer.visibility = View.GONE
                            changeStatusBarColor(change = false)
                        }
                    }
                }
            }
            weatherInfo.observe(viewLifecycleOwner) { weatherInfo ->
                if (weatherInfo.cityName.isNotBlank()) {
                    weatherInfoArgs = weatherInfo
                    binding.apply {
                        homeDetailsContainer.visibility = View.VISIBLE
                        currentCityNameTxt.text = weatherInfo.cityName
                    }
                }
            }
            cityInfoError.observe(viewLifecycleOwner) { message ->
                showToast(message)
                binding.cityNameTil.requestFocus()
                binding.homeContainer.visibility = View.VISIBLE
            }
            cityInfoLoading.observe(viewLifecycleOwner) { isLoading ->
                isShowLoading(isLoading)
            }
        }
    }

    private fun changeStatusBarColor(change: Boolean) {
        val color = if (change) {
            resources.getColor(R.color.blue_sky)
        } else {
            resources.getColor(R.color.white)
        }
        activity?.window?.statusBarColor = color
    }

    private fun isShowLoading(loading: Boolean) {
        when(loading) {
            true -> {
                binding.apply {
                    progressAnimation.visibility = View.VISIBLE
                    homeContainer.visibility = View.GONE
                }
            }
            else -> {
                binding.apply {
                    progressAnimation.visibility = View.GONE
                }
            }
        }
    }

    private fun showToast(message: String) {
        if (message.isNotBlank()) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun setListeners() {
        binding.apply {
            searchBtn.isEnabled = false
            cityNameTil.helperText = resources.getString(R.string.city_name_help_text)
            cityNameTiet.addTextChangedListener(
                onTextChanged = { text, _, _, _ ->
                    searchBtn.isEnabled = text.toString().length >= 3
                }
            )
            searchBtn.setOnClickListener {
                if (isValidCityName()) {
                    homeViewModel.getLatLonCity(cityNameTiet.text.toString())
                    cityNameTiet.text?.clear()
                    cityNameTiet.requestFocus()
                    hideKeyboard(it)
                }
            }
            detailsBtn.setOnClickListener {
                if (weatherInfoArgs != null) {
                    goToDetailsScreen()
                }
            }
            searchOtherBtn.setOnClickListener {
                homeDetailsContainer.visibility = View.GONE
                homeContainer.visibility = View.VISIBLE
                cityNameTiet.text?.clear()
                showKeyboard(cityNameTiet)
            }
        }
    }

    private fun goToDetailsScreen() {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(weatherInfoArgs)
        findNavController().navigate(action)
        clearData()
    }

    private fun clearData() {
        homeViewModel.clearCityInfos()
        binding.currentCityNameTxt.text = ""
    }

    private fun isValidCityName(): Boolean =
        binding.cityNameTiet.text?.isNotBlank() ?: false

    override fun onResume() {
        super.onResume()
        changeStatusBarColor(change = false)
        binding.apply {
            showKeyboard(cityNameTiet)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}