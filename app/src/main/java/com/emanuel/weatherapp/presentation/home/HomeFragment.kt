package com.emanuel.weatherapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emanuel.weatherapp.R
import com.emanuel.weatherapp.databinding.FragmentHomeBinding
import com.emanuel.weatherapp.domain.model.CityInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()
    private var cityInfoArgs = CityInfo()

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
            cityInfo.observe(viewLifecycleOwner) { cityInfo ->
                if (cityInfo.name.isNotBlank()) {
                    cityInfoArgs = cityInfo
                    goToDetailsScreen()
                }
            }
            cityInfoError.observe(viewLifecycleOwner) { message ->
                showToast(message)
            }
            cityInfoErrorLoading.observe(viewLifecycleOwner) { isLoading ->
                isShowLoading(isLoading)
            }
        }
    }

    private fun isShowLoading(loading: Boolean) {
        when(loading) {
            true -> {
                binding.homeContainer.visibility = View.GONE
                binding.homeProgress.visibility = View.VISIBLE
            }
            else -> {
                binding.homeContainer.visibility = View.VISIBLE
                binding.homeProgress.visibility = View.GONE
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
                }
            }
        }
    }

    private fun goToDetailsScreen() {
        cityInfoArgs.let {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                it.name,
                it.lat,
                it.lon
            )
            findNavController().navigate(action)
            homeViewModel.clearCityInfo()
        }
    }

    private fun isValidCityName(): Boolean =
        binding.cityNameTiet.text?.isNotBlank() ?: false


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}