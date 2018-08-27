package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

//"womanCount": 0,
//"manCount": 0,
//"attendCount": 0,
//"personLimit": 40,
//"ageAverage": 0,
//"name": "temp",
//"nickname": "temp",
//"img": "temp"

class FriendInfoData() : Parcelable{
    var womanCount : Int? = null
    var manCount : Int? = null
    var attendCount : Int? = null
    var personLimit : Int? = null
    var ageAverage : Int? = null

    var name: String? = null
    var nickname: String? = null
    var img: String? = null

    constructor(parcel: Parcel) : this() {
        womanCount = parcel.readValue(Int::class.java.classLoader) as? Int
        manCount = parcel.readValue(Int::class.java.classLoader) as? Int
        attendCount = parcel.readValue(Int::class.java.classLoader) as? Int
        personLimit = parcel.readValue(Int::class.java.classLoader) as? Int
        ageAverage = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
        nickname = parcel.readString()
        img = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(womanCount)
        parcel.writeValue(manCount)
        parcel.writeValue(attendCount)
        parcel.writeValue(personLimit)
        parcel.writeValue(ageAverage)
        parcel.writeString(name)
        parcel.writeString(nickname)
        parcel.writeString(img)
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