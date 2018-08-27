package com.youth.farm_volunteering.myactivity

import android.os.Parcel
import android.os.Parcelable


class MyActivityData() : Parcelable {
    var startDate: String? = null
    var endDate: String? = null
    var addr : String? =null
    var period : String? =null
    var name : String? =null
    var price : Int? =null
    var personLimit : Int? =null
    var nhIdx : Int? = null
    var idx : Int? =null
    var img : String? =null
    var schState : Int? = null
    var Astate : Int? = null
    var currentPerson : Int? =null
    var person : Int? =null
    var rState : Int? =null
    var state : Int? =null

    constructor(parcel: Parcel) : this() {
        startDate = parcel.readString()
        endDate = parcel.readString()
        addr = parcel.readString()
        period = parcel.readString()
        name = parcel.readString()
        state = parcel.readValue(Int::class.java.classLoader) as? Int
        price = parcel.readValue(Int::class.java.classLoader) as? Int
        currentPerson = parcel.readValue(Int::class.java.classLoader) as? Int
        person = parcel.readValue(Int::class.java.classLoader) as? Int
        personLimit = parcel.readValue(Int::class.java.classLoader) as? Int
        idx = parcel.readValue(Int::class.java.classLoader) as? Int
        img = parcel.readString()
        rState = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeString(addr)
        parcel.writeString(period)
        parcel.writeString(name)
        parcel.writeValue(state)
        parcel.writeValue(price)
        parcel.writeValue(currentPerson)
        parcel.writeValue(person)
        parcel.writeValue(personLimit)
        parcel.writeValue(idx)
        parcel.writeString(img)
        parcel.writeValue(rState)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyActivityData> {
        override fun createFromParcel(parcel: Parcel): MyActivityData {
            return MyActivityData(parcel)
        }

        override fun newArray(size: Int): Array<MyActivityData?> {
            return arrayOfNulls(size)
        }
    }

}