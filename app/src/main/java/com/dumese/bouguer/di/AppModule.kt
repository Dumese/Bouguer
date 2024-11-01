package com.dumese.bouguer.di

import android.content.Context
import com.dumese.bouguer.BuildConfig.QWeather_host
import com.dumese.bouguer.network.ApiClient
import com.dumese.bouguer.network.ApiInterface
import com.dumese.bouguer.utils.MoshiUtils
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = MoshiUtils.getMoshi()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, @ApplicationContext context: Context): Retrofit =
        ApiClient(context).getRetrofit(moshi)

    @Singleton
    @Provides
    fun provideGithubApiInterface(
        moshi: Moshi,
        @ApplicationContext context: Context
    ): ApiInterface = ApiClient(context).getRetrofit(
        moshi,
        QWeather_host
    ).create(ApiInterface::class.java)
}