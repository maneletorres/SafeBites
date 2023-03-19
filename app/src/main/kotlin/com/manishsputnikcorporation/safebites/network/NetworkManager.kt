package com.manishsputnikcorporation.safebites.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import android.net.NetworkInfo
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import com.manishsputnikcorporation.safebites.domain.error.exception.NetworkException

interface NetworkManager {

  @Throws(NetworkException::class) fun checkInternetConnection()

  @Throws(NetworkException::class) fun checkWiFi(): Boolean
}

class NetworkManagerImpl(private val context: Context) : NetworkManager {

  override fun checkInternetConnection() {
    if (!isInternetAvailable(context)) throw NetworkException("Not available")
  }

  override fun checkWiFi(): Boolean {
    checkInternetConnection()
    return isWifiConnected(context)
  }

  companion object {

    private fun isInternetAvailable(context: Context): Boolean {
      val networkInfo: NetworkInfo? = getActiveNetworkInfo(context)
      return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    private fun isWifiConnected(context: Context): Boolean {
      val networkInfo: NetworkInfo? = getActiveNetworkInfo(context)
      return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    // TODO: to review!
    private fun isNetworkAvailable(context: Context): Boolean {
      with(context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager) {
        return if (SDK_INT >= M) {
          val networkCapabilities: NetworkCapabilities =
              getNetworkCapabilities(activeNetwork) ?: return false
          when {
            networkCapabilities.hasTransport(TRANSPORT_WIFI) ||
                networkCapabilities.hasTransport(TRANSPORT_CELLULAR) ||
                networkCapabilities.hasTransport(TRANSPORT_ETHERNET) ||
                networkCapabilities.hasTransport(TRANSPORT_BLUETOOTH) -> true
            else -> false
          }
        } else activeNetworkInfo?.isConnected ?: return false
      }
    }

    private fun getActiveNetworkInfo(context: Context) =
        (context.getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager)?.activeNetworkInfo
  }
}
