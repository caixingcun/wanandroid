package com.example.cxc.playandroidkotlin.model


data class PagesBean<T>(
        val curPage: Int,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int,
        val datas: List<T>

)