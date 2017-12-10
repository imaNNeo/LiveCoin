package com.base.baseproject.data.api.retrofit.models

/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
class RequestObjects {
    class LoginFields {
        var username: String? = null
        var password: String? = null
    }

    data class Crypto(val id : String,
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
    )
}