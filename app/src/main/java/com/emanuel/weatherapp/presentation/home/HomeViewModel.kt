package com.emanuel.weatherapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuel.weatherapp.domain.model.CityInfo
import com.emanuel.weatherapp.domain.model.WeatherInfo
import com.emanuel.weatherapp.domain.usecases.GetCurrentWeatherUseCase
import com.emanuel.weatherapp.domain.usecases.GetLatLonWithCityNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatLonWithCityNameUseCase: GetLatLonWithCityNameUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _cityName = MutableLiveData("")

    private val _cityInfo = MutableLiveData<CityInfo>()
    val cityInfo: LiveData<CityInfo> = _cityInfo

    private val _weatherInfo = MutableLiveData<WeatherInfo>()
    val weatherInfo: LiveData<WeatherInfo> = _weatherInfo

    private val _cityInfoError = MutableLiveData("")
    val cityInfoError: LiveData<String> = _cityInfoError

    private val _cityInfoLoading = MutableLiveData(false)
    val cityInfoLoading: LiveData<Boolean> = _cityInfoLoading

    fun getLatLonCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _cityInfoLoading.postValue(true)
            _cityName.postValue(city)
            val cityInfo = getLatLonWithCityNameUseCase(city)
            delay(500)
            if (cityInfo.name.isNotBlank()) {
                _cityInfo.postValue(cityInfo)
                getCurrentWeather(cityInfo.lat, cityInfo.lon)
            } else {
                _cityInfoLoading.postValue(false)
                _cityInfoError.postValue("City not found. Try again!")
            }
        }
    }

    private suspend fun getCurrentWeather(lat: Float, lon: Float) {
        val weatherInfo = getCurrentWeatherUseCase(lat, lon)
        _weatherInfo.postValue(weatherInfo)
        _cityInfoLoading.postValue(false)
    }

    fun clearCityInfos() {
        _weatherInfo.value = WeatherInfo()
        _cityInfo.value = CityInfo()
    }
}