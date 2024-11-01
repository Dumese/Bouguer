package com.dumese.bouguer.network

import android.content.Context
import com.dumese.bouguer.BuildConfig.QWeather_host
import com.dumese.bouguer.config.AppConfig.CACHE_MAX_SIZE
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiClient(
    @ApplicationContext private val context: Context
) {
    private fun buildClient(
        headers: Map<String, String> = mapOf()
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
                .addHeader("Accept", "application/json")

            for (header in headers) {
                requestBuilder.addHeader(header.key, header.value)
            }

            val request = requestBuilder.build()

            chain.proceed(request)
        }
        .cache(Cache(context.cacheDir, CACHE_MAX_SIZE))
        .build()

    fun getRetrofit(
        moshi: Moshi,
        baseUrl: String = QWeather_host,
        headers: Map<String, String> = mapOf()
    ): Retrofit = Retrofit.Builder()
        .client(buildClient(headers))
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(baseUrl)
        .build()
}