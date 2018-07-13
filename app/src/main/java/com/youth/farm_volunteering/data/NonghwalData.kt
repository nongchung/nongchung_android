package com.youth.farm_volunteering.data

import java.io.Serializable

open class NonghwalData : Serializable {
    var idx: Int? = null
    var name: String? = null
    var addr: String? = null
    var price: Int? = null
    var period: String? = null
    var isBooked: Int? = null
    var addrIdx: Int? = 0
    var img: String? = null
    var star: Double? = null

    open fun getRealId(): Int? {
        return idx
    }
}