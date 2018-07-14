package com.youth.farm_volunteering.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Lee_wonjun on 2018-07-14.
 */
class UserData() : Parcelable {
    var name: String? = null
    var birthYear: String? = null
    var birthMonth: String? = null
    var birthDay: String? = null
    var sex: Int? = null
    var mail: String? = null
    var img: String? = null
    var hp: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        birthYear = parcel.readString()
        birthMonth = parcel.readString()
        birthDay = parcel.readString()
        sex = parcel.readValue(Int::class.java.classLoader) as? Int
        mail = parcel.readString()
        img = parcel.readString()
        hp = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(birthYear)
        parcel.writeString(birthMonth)
        parcel.writeString(birthDay)
        parcel.writeValue(sex)
        parcel.writeString(mail)
        parcel.writeString(img)
        parcel.writeString(hp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}