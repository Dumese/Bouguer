package com.dumese.bouguer.models

data class Weather(
    val now: Now,
    val hourly: List<Hourly>,
    val daily: List<Daily>
)
