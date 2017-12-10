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

    data class GetCoinItemsFields(val limit : Int)

}