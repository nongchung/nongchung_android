package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

class NewNonghwalData() : Parcelable {
    var nhIdx : Int? = null
    var name: String? = null
    var price: Int? = null
    var star: Int? = null
    var period: String? = null
    var addr: String? = null
    var img: String? = null
    var idx: Int? = null
    var isBooked: Int? = null

    constructor(parcel: Parcel) : this() {
        nhIdx = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        price = parcel.readValue(Int::class.java.classLoader) as? Int
        star = parcel.readValue(Int::class.java.classLoader) as? Int
        period = parcel.readString()
        addr = parcel.readString()
        img = parcel.readString()
        idx = parcel.readValue(Int::class.java.classLoader) as? Int
        isBooked = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(nhIdx)
        parcel.writeString(name)
        parcel.writeValue(price)
        parcel.writeValue(star)
        parcel.writeString(period)
        parcel.writeString(addr)
        parcel.writeString(img)
        parcel.writeValue(idx)
        parcel.writeValue(isBooked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewNonghwalData> {
        override fun createFromParcel(parcel: Parcel): NewNonghwalData {
            return NewNonghwalData(parcel)
        }

        override fun newArray(size: Int): Array<NewNonghwalData?> {
            return arrayOfNulls(size)
        }
    }
}