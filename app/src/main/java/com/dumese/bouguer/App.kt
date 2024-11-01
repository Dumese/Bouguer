package com.dumese.bouguer

import android.app.Application
import com.dumese.bouguer.BuildConfig.Tencent_key
import com.tencent.map.geolocation.TencentLocationManager
import com.tencent.map.geolocation.TencentLocationManagerOptions
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
        TencentLocationManagerOptions.setKey(Tencent_key)
        TencentLocationManager.setUserAgreePrivacy(true)
    }
}