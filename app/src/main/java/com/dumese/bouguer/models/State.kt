package com.dumese.bouguer.models

import java.util.Date

sealed class State {
    data class SUCCESS(val msg: String = "成功", val date: Date = Date()) : State()
    data class FAIL(val msg: String = "失败", val date: Date = Date()) : State()
    data class LOADING(val msg: String = "加载中", val date: Date = Date()) : State()
}