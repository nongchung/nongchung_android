package com.youth.farm_volunteering.login

object LoginToken {
    val PREF_KEY = "PREF_LOGIN_KEY"
    var token: String? = null
    var logined: Boolean = false
        get() = token != null
}