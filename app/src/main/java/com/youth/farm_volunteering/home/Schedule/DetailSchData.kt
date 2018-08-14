package com.youth.farm_volunteering.home.Schedule

import android.os.Parcel
import android.os.Parcelable

class DetailSchData() : Parcelable {
    var time:String? = null
    var activity:String? = null

    constructor(parcel: Parcel) : this() {
        time = parcel.readString()
        activity = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(time)
        parcel.writeString(activity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailSchData> {
        override fun createFromParcel(parcel: Parcel): DetailSchData {
            return DetailSchData(parcel)
        }

        override fun newArray(size: Int): Array<DetailSchData?> {
            return arrayOfNulls(size)
        }
    }
}