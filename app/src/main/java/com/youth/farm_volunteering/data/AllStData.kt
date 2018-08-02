package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

class AllStData() : Parcelable {
    var idx: Int? = null
    var period : String? = null
    var endDate : String? = null
    var state: Int? = null
    var startDate: String? = null
    var availPerson : Int? = null

    constructor(parcel: Parcel) : this() {
        idx = parcel.readValue(Int::class.java.classLoader) as? Int
        period = parcel.readString()
        endDate = parcel.readString()
        state = parcel.readValue(Int::class.java.classLoader) as? Int
        startDate = parcel.readString()
        availPerson = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(idx)
        parcel.writeString(period)
        parcel.writeString(endDate)
        parcel.writeValue(state)
        parcel.writeString(startDate)
        parcel.writeValue(availPerson)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AllStData> {
        override fun createFromParcel(parcel: Parcel): AllStData {
            return AllStData(parcel)
        }

        override fun newArray(size: Int): Array<AllStData?> {
            return arrayOfNulls(size)
        }
    }


}