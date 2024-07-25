package uz.abubakir_khakimov.recipes.common_impl

import android.content.Context
import android.net.NetworkCapabilities
import uz.abubakir_khakimov.recipes.common.managers.CheckNetworkManager

class CheckNetworkManagerImpl(
    private val context: Context
): CheckNetworkManager {

    override fun isNetworkConnected(): Boolean{
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as android.net.ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}