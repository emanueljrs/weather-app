package com.emanuel.weatherapp.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuel.weatherapp.domain.model.CityInfo
import com.emanuel.weatherapp.domain.usecases.GetLatLonWithCityNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatLonWithCityNameUseCase: GetLatLonWithCityNameUseCase
) : ViewModel() {

    private val _cityName = MutableLiveData("")

    private val _cityInfo = MutableLiveData<CityInfo>()
    val cityInfo: LiveData<CityInfo> = _cityInfo

    private val _cityInfoError = MutableLiveData("")
    val cityInfoError: LiveData<String> = _cityInfoError

    private val _cityInfoLoading = MutableLiveData(false)
    val cityInfoErrorLoading: LiveData<Boolean> = _cityInfoLoading

    fun getLatLonCity(city: String) {
        viewModelScope.launch {
            _cityInfoLoading.postValue(true)
            _cityName.value = city
            val cityInfo = getLatLonWithCityNameUseCase(city)
            delay(500)
            _cityInfoLoading.postValue(false)
            if (cityInfo.name.isNotBlank()) {
                _cityInfo.postValue(cityInfo)
            } else {
                _cityInfoError.postValue("City not found. Try again!")
            }
        }
    }

    fun clearCityInfo() {
        _cityInfo.value = CityInfo()
    }
}