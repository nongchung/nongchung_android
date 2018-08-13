package com.youth.farm_volunteering.home.ThemaNonghwal

import android.os.Parcel
import android.os.Parcelable
import com.youth.farm_volunteering.data.NonghwalData

open class ThemaListData() : Parcelable, NonghwalData() {
    var fImg : String? = null
    var nhIdx: Int? = null
    var newState : Int? = null

    constructor(parcel: Parcel) : this() {
        fImg = parcel.readString()
        addr = parcel.readString()
        name = parcel.readString()
        price = parcel.readValue(Int::class.java.classLoader) as? Int
        period = parcel.readString()
        nhIdx = parcel.readValue(Int::class.java.classLoader) as? Int
        newState = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fImg)
        parcel.writeString(addr)
        parcel.writeString(name)
        parcel.writeValue(price)
        parcel.writeString(period)
        parcel.writeValue(nhIdx)
        parcel.writeValue(newState)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ThemaListData> {
        override fun createFromParcel(parcel: Parcel): ThemaListData {
            return ThemaListData(parcel)
        }

        override fun newArray(size: Int): Array<ThemaListData?> {
            return arrayOfNulls(size)
        }
    }

    override fun getRealId(): Int? {
        return nhIdx
    }

    override fun getRealImg(): String? {
        return fImg
    }

//    override fun getThemeNewState() : Int{
//        return newState!!
//    }
//
//    open fun getfImg() : String{
//        return fImg!!
//    }
}