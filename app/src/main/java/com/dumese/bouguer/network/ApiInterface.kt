package com.dumese.bouguer.network

import com.dumese.bouguer.models.QWeatherRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/v7/weather/24h")
    suspend fun getHourly(
        @Query("key") key: String,
        @Query("location") location: String,
    ): Response<QWeatherRepoResponse>

    @GET("/v7/weather/now")
    suspend fun getNow(
        @Query("key") key: String,
        @Query("location") location: String,
    ): Response<QWeatherRepoResponse>

    @GET("/v7/weather/7d")
    suspend fun getDaily(
        @Query("key") key: String,
        @Query("location") location: String,
    ): Response<QWeatherRepoResponse>
}