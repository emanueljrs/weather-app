package com.emanuel.weatherapp.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuel.weatherapp.domain.usecases.GetCurrentWeatherUseCase
import com.emanuel.weatherapp.domain.usecases.GetLatLonWithCityNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getLatLonWithCityNameUseCase: GetLatLonWithCityNameUseCase
) : ViewModel() {

    private val _cityName = MutableLiveData("")
    val cityName: LiveData<String> = _cityName

    fun getInfoWeather(city: String) {
        viewModelScope.launch {
            _cityName.value = city

            val cityInfo = getLatLonWithCityNameUseCase(city)
            Log.d("HomeViewModel", "${cityInfo.name}, ${cityInfo.lat}, ${cityInfo.lon}")
            val result = getCurrentWeatherUseCase(
                lat = cityInfo.lat,
                lng = cityInfo.lon
            )
            Log.d("HomeViewModel", "${result.cityName}, ${result.temperature}")
        }
    }
}