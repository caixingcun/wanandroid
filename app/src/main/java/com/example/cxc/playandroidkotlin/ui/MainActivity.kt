package com.example.cxc.playandroidkotlin.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.cxc.playandroidkotlin.R
import com.example.cxc.playandroidkotlin.R.id.*
import com.example.cxc.playandroidkotlin.adapter.FirstArticleAdapter
import com.example.cxc.playandroidkotlin.model.BaseBean
import com.example.cxc.playandroidkotlin.model.FirstArticleBean
import com.example.cxc.playandroidkotlin.model.PagesBean
import com.example.cxc.playandroidkotlin.net.ArticleService
import com.example.cxc.playandroidkotlin.toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    lateinit var list: MutableList<FirstArticleBean>
    lateinit var firstArticleAdapter: FirstArticleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()

    }

    private fun initData() {

        Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleService::class.java)
                .firstArticleList("1").enqueue(object : Callback<BaseBean<PagesBean<FirstArticleBean>>> {
                    override fun onFailure(call: Call<BaseBean<PagesBean<FirstArticleBean>>>?, t: Throwable) {

                    }

                    override fun onResponse(call: Call<BaseBean<PagesBean<FirstArticleBean>>>?, response: Response<BaseBean<PagesBean<FirstArticleBean>>>) {


                        list.clear()
                        list.addAll(response.body()!!.data.datas)
                        firstArticleAdapter.notifyDataSetChanged()
                    }
                })


    }


    private fun initView() {
        list = ArrayList()
        firstArticleAdapter = FirstArticleAdapter(R.layout.item_first_article, list)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = firstArticleAdapter

    }
}
