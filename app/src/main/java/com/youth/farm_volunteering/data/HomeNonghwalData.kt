package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

open class HomeNonghwalData() : NonghwalData(), Parcelable {
    var nhIdx: Int? = null

    constructor(parcel: Parcel) : this() {
        nhIdx = parcel.readValue(Int::class.java.classLoader) as? Int
        idx = parcel.readValue(Int::class.java.classLoader) as? Int
        star = parcel.readValue(Double::class.java.classLoader) as? Double
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

    companion object CREATOR : Parcelable.Creator<HomeNonghwalData> {
        override fun createFromParcel(parcel: Parcel): HomeNonghwalData {
            return HomeNonghwalData(parcel)
        }

        override fun newArray(size: Int): Array<HomeNonghwalData?> {
            return arrayOfNulls(size)
        }
    }

    override fun getRealId(): Int? {
        return nhIdx
    }
}