package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

class NhInfoData() : Parcelable {
    var addr: String? = null
    var name: String? = null
    var star: Int? = null
    var description: String? = null
    var price: Int? = null
    var period: String? = null

    constructor(parcel: Parcel) : this() {
        addr = parcel.readString()
        name = parcel.readString()
        star = parcel.readValue(Int::class.java.classLoader) as? Int
        description = parcel.readString()
        price = parcel.readValue(Int::class.java.classLoader) as? Int
        period = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(addr)
        parcel.writeString(name)
        parcel.writeValue(star)
        parcel.writeString(description)
        parcel.writeValue(price)
        parcel.writeString(period)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NhInfoData> {
        override fun createFromParcel(parcel: Parcel): NhInfoData {
            return NhInfoData(parcel)
        }

        override fun newArray(size: Int): Array<NhInfoData?> {
            return arrayOfNulls(size)
        }
    }
}