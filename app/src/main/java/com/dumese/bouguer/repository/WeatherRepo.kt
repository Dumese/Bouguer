package com.dumese.bouguer.repository

import android.content.Context
import com.dumese.bouguer.network.ApiInterface
import com.dumese.bouguer.network.SafeApiRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: ApiInterface
) {
    suspend fun getHourly(
        key: String,
        location: String
    ) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getHourly(
                key = key,
                location = location
            )
        }
    }

    suspend fun getNow(
        key: String,
        location: String
    ) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getNow(
                key = key,
                location = location
            )
        }
    }

    suspend fun getDaily(
        key: String,
        location: String
    ) = withContext(Dispatchers.IO) {
        SafeApiRequest.apiRequest(context) {
            api.getDaily(
                key = key,
                location = location
            )
        }
    }
}