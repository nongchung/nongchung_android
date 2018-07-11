package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

class WeekNonghwalData() : Parcelable {
    var nhIdx : Int? = null
    var idx: Int? = null
    var star: Float? = null
    var price: Int? = null
    var name: String? = null
    var period: String? = null
    var addr: String? = null
    var img: String? = null
    var isBooked : Int? = null

    constructor(parcel: Parcel) : this() {
        nhIdx = parcel.readValue(Int::class.java.classLoader) as? Int
        idx = parcel.readValue(Int::class.java.classLoader) as? Int
        star = parcel.readValue(Float::class.java.classLoader) as? Float
        price = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        period = parcel.readString()
        addr = parcel.readString()
        img = parcel.readString()
        isBooked = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(nhIdx)
        parcel.writeValue(idx)
        parcel.writeValue(star)
        parcel.writeValue(price)
        parcel.writeString(name)
        parcel.writeString(period)
        parcel.writeString(addr)
        parcel.writeString(img)
        parcel.writeValue(isBooked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeekNonghwalData> {
        override fun createFromParcel(parcel: Parcel): WeekNonghwalData {
            return WeekNonghwalData(parcel)
        }

        override fun newArray(size: Int): Array<WeekNonghwalData?> {
            return arrayOfNulls(size)
        }
    }
}