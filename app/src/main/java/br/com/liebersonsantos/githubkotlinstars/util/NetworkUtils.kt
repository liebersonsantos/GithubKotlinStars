package br.com.liebersonsantos.githubkotlinstars.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
fun hasInternet(context: Context?): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    val network = connectivityManager?.activeNetwork
    val connection = connectivityManager?.getNetworkCapabilities(network)
    return connection != null && (
            connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
}

interface IConnectionUtils {
    fun checkConnection()
}