package com.youth.farm_volunteering.data

import com.youth.farm_volunteering.Home.Schedule.DetailSchData

class DetailNonghwalResponseData {
    var message: String? = null
    var image: List<String>? = null
    var nhInfo: NhInfoData? = null
    var friendsInfo: ArrayList<FriendInfoData>? = null
    var farmerInfo: FarmInfoData? = null
    var schedule : ArrayList<DetailSchData>? = null
    var nearestStartDate : String? = null
    var nearestEndDate : String? = null
    var allStartDate : ArrayList<AllStData>? = null
    var myScheduleActivities : ArrayList<Int>? = null
}