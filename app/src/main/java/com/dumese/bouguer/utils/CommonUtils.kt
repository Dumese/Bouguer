package com.dumese.bouguer.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

object CommonUtils {
    @SuppressLint("MissingPermission")
    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo

        return activeNetwork?.isConnected == true
    }
}