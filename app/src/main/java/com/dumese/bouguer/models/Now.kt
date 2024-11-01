package com.dumese.bouguer.models

data class Now(
    val cloud: String,
    val dew: String,
    val feelsLike: String,
    val humidity: String,
    val icon: String,
    val obsTime: String,
    val precip: String,
    val pressure: String,
    val temp: String,
    val text: String,
    val vis: String,
    val wind360: String,
    val windDir: String,
    val windScale: String,
    val windSpeed: String
) {
    companion object {
        fun getDefault(): Now {
            return Now(
                cloud = "",
                dew = "",
                feelsLike = "",
                humidity = "",
                icon = "",
                obsTime = "",
                precip = "",
                pressure = "",
                temp = "",
                text = "",
                vis = "",
                wind360 = "",
                windDir = "",
                windScale = "",
                windSpeed = ""
            )
        }
    }
}