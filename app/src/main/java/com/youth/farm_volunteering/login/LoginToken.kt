package com.youth.farm_volunteering.login

object LoginToken {
    val PREF_KEY = "PREF_LOGIN_KEY"
    var token: String? = null
        get() = null//"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkeCI6MzMsImlhdCI6MTUzMDYxOTYwOCwiZXhwIjoxNTMzMjExNjA4fQ.oi8c_p2ky2zGADCuUE0zQOJ_I0JHgjNt4L1bDr2Sjqg"
    var logined: Boolean = false
        get() = token != null
}