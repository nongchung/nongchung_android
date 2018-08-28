package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

class FriendInfoData() : Parcelable{
    var womanCount : Int? = null
    var manCount : Int? = null
    var attendCount : Int? = null
    var personLimit : Int? = null
    var ageAverage : Float? = null

    constructor(parcel: Parcel) : this() {
        womanCount = parcel.readValue(Int::class.java.classLoader) as? Int
        manCount = parcel.readValue(Int::class.java.classLoader) as? Int
        attendCount = parcel.readValue(Int::class.java.classLoader) as? Int
        personLimit = parcel.readValue(Int::class.java.classLoader) as? Int
        ageAverage = parcel.readValue(Int::class.java.classLoader) as? Float
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(womanCount)
        parcel.writeValue(manCount)
        parcel.writeValue(attendCount)
        parcel.writeValue(personLimit)
        parcel.writeValue(ageAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FriendInfoData> {
        override fun createFromParcel(parcel: Parcel): FriendInfoData {
            return FriendInfoData(parcel)
        }

        override fun newArray(size: Int): Array<FriendInfoData?> {
            return arrayOfNulls(size)
        }
    }
}