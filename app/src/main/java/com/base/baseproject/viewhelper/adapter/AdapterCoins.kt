package com.base.baseproject.viewhelper.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.baseproject.R
import com.base.baseproject.data.api.retrofit.models.ResponseObjects
import com.base.baseproject.utils.ImageHandler
import com.base.baseproject.viewhelper.widget.AppImageView
import com.base.baseproject.viewhelper.widget.AppTextView
import java.text.NumberFormat
import java.util.*

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
class AdapterCoins : BaseRecyclerAdapter<AdapterCoins.ViewHolderCoin, ResponseObjects.CoinItem>() {

    lateinit var mContext : Context
    private var redColor : Int = 0
    private var greenColor : Int = 0

    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderCoin {
        mContext = parent!!.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.coin_item,parent,false)

        if(redColor==0) redColor = ContextCompat.getColor(mContext,R.color.red)
        if(greenColor==0) greenColor = ContextCompat.getColor(mContext,R.color.green)

        return ViewHolderCoin(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCoin?, position: Int) {
        val coin = getItem(position)
        ImageHandler.getmInstance(mContext).loadResImage(R.drawable.bitcoin,holder?.ivImage)
        holder?.tvName?.text = "${coin.name} (${coin.symbol})"
        holder?.tvPrice?.text = "${currencyFormatter.format(coin.price_usd)}"

        holder?.tv1HPercent?.text = "${coin.percent_change_1h} %"
        holder?.tv1HPercent?.setTextColor(if (coin.percent_change_1h>=0) greenColor else redColor)

        holder?.tv24HPercent?.text = "${coin.percent_change_24h} %"
        holder?.tv24HPercent?.setTextColor(if (coin.percent_change_24h>=0) greenColor else redColor)

        holder?.tv7DPercent?.text = "${coin.percent_change_7d} %"
        holder?.tv7DPercent?.setTextColor(if (coin.percent_change_7d>=0) greenColor else redColor)
    }


    class ViewHolderCoin(itemView: View?) : RecyclerView.ViewHolder(itemView){
        var tvName = itemView?.findViewById<AppTextView>(R.id.tv_name)
        var ivImage = itemView?.findViewById<AppImageView>(R.id.iv_image)
        var tvPrice = itemView?.findViewById<AppTextView>(R.id.tv_price)
        var tv1HPercent = itemView?.findViewById<AppTextView>(R.id.tv_1hPercent)
        var tv24HPercent = itemView?.findViewById<AppTextView>(R.id.tv_24hPercent)
        var tv7DPercent = itemView?.findViewById<AppTextView>(R.id.tv_7dPercent)
    }
}