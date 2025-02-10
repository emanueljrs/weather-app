package com.emanuel.weatherapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED
import android.os.Build
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ConnectivityManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val _isConnected = MutableStateFlow<Boolean?>(null)
    val isConnected: Flow<Boolean?> = _isConnected

    init {
        hasInternet()
    }

    private fun hasInternet() {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (connectivityManager.activeNetwork == null) {
            _isConnected.value = false
        }

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _isConnected.value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    capabilities != null &&
                            // verifica se tem rede. Ex: Wifi, 3G, etc
                            capabilities.hasCapability(NET_CAPABILITY_INTERNET) &&
                            // Verifica se realmente consegue fazer requisições, se ainda não precisa de
                            // login por exemplo. As vezes já está conectado mas ainda não foi liberado a
                            // internet
                            capabilities.hasCapability(NET_CAPABILITY_VALIDATED)
                } else {
                    @Suppress("DEPRECATION")
                    connectivityManager.activeNetworkInfo?.isConnected == true
                }
                Log.d("ConnectivityManager", "onAvailable called: ${_isConnected.value}")
            }

            override fun onLost(network: Network) {
                _isConnected.value = false
                Log.d("ConnectivityManager", "onLost called: ${_isConnected.value}")
            }
        })
    }
}