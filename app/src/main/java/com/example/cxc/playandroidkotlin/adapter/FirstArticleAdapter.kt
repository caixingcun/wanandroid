package com.example.cxc.playandroidkotlin.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.cxc.playandroidkotlin.R
import com.example.cxc.playandroidkotlin.model.FirstArticleBean

class FirstArticleAdapter(layoutResId: Int, list: MutableList<FirstArticleBean>) : BaseQuickAdapter<FirstArticleBean, BaseViewHolder>(layoutResId, list) {

    override fun convert(helper: BaseViewHolder?, item: FirstArticleBean?) {
        helper!!.setText(R.id.tv, item!!.title)
    }

}


