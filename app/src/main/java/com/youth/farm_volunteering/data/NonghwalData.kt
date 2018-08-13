package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable
import com.youth.farm_volunteering.home.ThemaNonghwal.ThemaListData
import java.io.Serializable

open class NonghwalData() : Parcelable {

    var idx: Int? = null
    var name: String? = null
    var addr: String? = null
    var price: Int? = null
    var period: String? = null
    var isBooked: Int? = null
    var addrIdx: Int? = 0
    var img: String? = null
    var star: Double? = null

    constructor(parcel: Parcel) : this() {
        idx = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        addr = parcel.readString()
        price = parcel.readValue(Int::class.java.classLoader) as? Int
        period = parcel.readString()
        isBooked = parcel.readValue(Int::class.java.classLoader) as? Int
        addrIdx = parcel.readValue(Int::class.java.classLoader) as? Int
        img = parcel.readString()
        star = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    open fun getRealId(): Int? {
        return idx
    }

    open fun getRealImg() : String?{
        return img
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(idx)
        parcel.writeString(name)
        parcel.writeString(addr)
        parcel.writeValue(price)
        parcel.writeString(period)
        parcel.writeValue(isBooked)
        parcel.writeValue(addrIdx)
        parcel.writeString(img)
        parcel.writeValue(star)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NonghwalData> {
        override fun createFromParcel(parcel: Parcel): NonghwalData {
            return NonghwalData(parcel)
        }

        override fun newArray(size: Int): Array<NonghwalData?> {
            return arrayOfNulls(size)
        }
    }

}