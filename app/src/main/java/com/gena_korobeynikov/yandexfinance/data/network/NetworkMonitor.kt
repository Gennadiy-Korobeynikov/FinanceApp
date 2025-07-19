package com.gena_korobeynikov.yandexfinance.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

interface NetworkMonitor {
    fun isConnected(): Boolean
    val networkAvailableFlow: Flow<Boolean>
}

@Singleton
class NetworkMonitorImpl @Inject constructor(
    private val context : Context,
) : NetworkMonitor {

    private val _networkStatus = MutableStateFlow(checkConnected())
    override val networkAvailableFlow: StateFlow<Boolean> = _networkStatus.asStateFlow()

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _networkStatus.value = true
        }

        override fun onLost(network: Network) {
            _networkStatus.value = checkConnected()
        }

        override fun onUnavailable() {
            _networkStatus.value = false
        }
    }

    init {
        registerCallback()
    }

    private fun registerCallback() {
        val request = NetworkRequest.Builder().build()
        connectivityManager?.registerNetworkCallback(request, networkCallback)
    }

    private fun checkConnected(): Boolean {
        val activeNetwork = connectivityManager?.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    override fun isConnected(): Boolean {
        Log.i("NetworkMonitor", "Checking network connection status : ${_networkStatus.value}")
        return _networkStatus.value
    }

}

