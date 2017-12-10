package com.base.baseproject.data.api.retrofit.models

import com.base.baseproject.viewhelper.adapter.BaseRecyclerAdapter

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
class ResponseObjects {
    class LoginResponse {
        var token: String? = null
        var success: Boolean = false
        var message: String? = null
    }

    class ErrorAnswer {
        var error: String? = null
        var error_description: String? = null
    }

    data class CoinItem(val id : String,
                      val name  : String,
                      val symbol : String,
                      val rank : Int,
                      val price_usd : Float,
                      val price_btc : Float,
                      val market_cap_usd : Float,
                      val available_supply : Float,
                      val total_supply : Float,
                      val max_supply : Float,
                      val percent_change_1h : Float,
                      val percent_change_24h : Float,
                      val percent_change_7d : Float,
                      val last_updated : Long
    ) : BaseRecyclerAdapter.IDiff{
        override fun areContentsTheSame(thisType: Any?): Boolean {
            if(thisType is CoinItem){
                return thisType.id == this.id
            }
            return false
        }

        override fun areItemsTheSame(thisType: Any?): Boolean {
            if(thisType is CoinItem){
                return thisType == this
            }
            return false
        }
    }

}
