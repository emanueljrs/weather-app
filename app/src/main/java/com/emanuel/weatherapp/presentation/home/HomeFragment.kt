package com.emanuel.weatherapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.emanuel.weatherapp.R
import com.emanuel.weatherapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel>()

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
        setListeners()
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

    private fun isValidCityName(): Boolean =
        binding.cityNameTiet.text?.isNotBlank() ?: false


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}