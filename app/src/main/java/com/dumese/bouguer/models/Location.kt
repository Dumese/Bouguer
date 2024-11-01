package com.dumese.bouguer.models

data class Location(
    val latitude : String = "",
    val longitude : String = "",
    val state: State = State.LOADING()
)
