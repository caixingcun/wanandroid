package com.example.cxc.playandroidkotlin.model


data class BaseBean<T>(
        val errorCode: Int,
        val errorMsg: String,
        var data: T
)
