package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

class FriendInfoData() : Parcelable {
    var name: String? = null
    var nickname: String? = null
    var img: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        nickname = parcel.readString()
        img = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
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