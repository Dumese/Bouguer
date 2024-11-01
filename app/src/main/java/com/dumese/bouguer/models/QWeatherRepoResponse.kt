package com.dumese.bouguer.models

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = false)
data class QWeatherRepoResponse(
    @Json(name = "code")
    val code: String,
    @Json(name = "fxLink")
    val fxLink: String = "",
    @Json(name = "refer")
    val refer: Refer?,
    @Json(name = "updateTime")
    val updateTime: String = "",
    @Json(name = "now")
    val now: Now = Now.getDefault(),
    @Json(name = "hourly")
    val hourly: List<Hourly> = listOf(),
    @Json(name = "daily")
    val daily: List<Daily> = listOf(),
)