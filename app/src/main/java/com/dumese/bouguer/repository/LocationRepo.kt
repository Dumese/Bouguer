package com.dumese.bouguer.repository

import android.content.Context
import android.os.Looper
import com.dumese.bouguer.models.Location
import com.dumese.bouguer.models.State
import com.tencent.map.geolocation.TencentLocation
import com.tencent.map.geolocation.TencentLocationListener
import com.tencent.map.geolocation.TencentLocationManager
import com.tencent.map.geolocation.TencentLocationRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationRepo @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val manager = TencentLocationManager.getInstance(context.applicationContext)
    private val request = TencentLocationRequest.create().setGpsFirst(true)

    fun getLocation(callback: (Location) -> Unit) {
        var location: Location

        manager.requestSingleFreshLocation(
            request,
            object : TencentLocationListener {
                override fun onLocationChanged(
                    tLocation: TencentLocation,
                    error: Int,
                    reason: String?
                ) {
                    if (error == TencentLocation.ERROR_OK) {
                        location = Location().copy(
                            latitude = tLocation.latitude.toString(),
                            longitude = tLocation.longitude.toString(),
                            state = State.SUCCESS()
                        )
                        callback(location)
                    } else {
                        location = Location().copy(
                            state = State.FAIL(msg = "失败:$reason")
                        )
                        callback(location)
                    }
                }

                override fun onStatusUpdate(p0: String?, p1: Int, p2: String?) {
                    /* undo */
                }
            },
            Looper.getMainLooper()
        )
    }
}