package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

class FarmInfoData() : Parcelable {
    var name : String ? = null
    var comment : String ? = null
    var img : String ? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        comment = parcel.readString()
        img = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(comment)
        parcel.writeString(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FarmInfoData> {
        override fun createFromParcel(parcel: Parcel): FarmInfoData {
            return FarmInfoData(parcel)
        }

        override fun newArray(size: Int): Array<FarmInfoData?> {
            return arrayOfNulls(size)
        }
    }
}