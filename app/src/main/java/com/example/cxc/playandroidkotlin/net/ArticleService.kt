package com.example.cxc.playandroidkotlin.net

import com.example.cxc.playandroidkotlin.model.BaseBean
import com.example.cxc.playandroidkotlin.model.FirstArticleBean
import com.example.cxc.playandroidkotlin.model.PagesBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {

    @GET("article/list/{page}/json")
    fun firstArticleList(@Path("page")page :String  ):Call<BaseBean<PagesBean<FirstArticleBean>>>

}