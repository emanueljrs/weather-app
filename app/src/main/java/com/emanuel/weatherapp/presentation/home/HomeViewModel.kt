package com.emanuel.weatherapp.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emanuel.weatherapp.domain.model.CityInfo
import com.emanuel.weatherapp.domain.usecases.GetLatLonWithCityNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatLonWithCityNameUseCase: GetLatLonWithCityNameUseCase
) : ViewModel() {

    private val _cityName = MutableLiveData("")
    val cityName: LiveData<String> = _cityName

    private val _cityInfo = MutableLiveData<CityInfo>()
    val cityInfo: LiveData<CityInfo> = _cityInfo

    fun getLatLonCity(city: String) {
        viewModelScope.launch {
            _cityName.value = city

            _cityInfo.postValue(getLatLonWithCityNameUseCase(city))
            Log.d("HomeViewModel", "${_cityInfo.value?.name}, ${_cityInfo.value?.lat}, ${_cityInfo.value?.lon}")
        }
    }
}