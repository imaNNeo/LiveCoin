package com.base.baseproject.viewhelper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.baseproject.R
import com.base.baseproject.data.api.retrofit.models.ResponseObjects

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
class AdapterCoins : BaseRecyclerAdapter<AdapterCoins.ViewHolderCoin, ResponseObjects.CoinItem>() {

    lateinit var mContext : Context
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderCoin {
        mContext = parent!!.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.coin_item,parent,false)
        return ViewHolderCoin(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCoin?, position: Int) {
    }


    class ViewHolderCoin(itemView: View?) : RecyclerView.ViewHolder(itemView)
}