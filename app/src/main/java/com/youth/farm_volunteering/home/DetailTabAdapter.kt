package com.youth.farm_volunteering.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.youth.farm_volunteering.expanded.ExpandFragment
import com.youth.farm_volunteering.home.Schedule.DetailSchData
import com.youth.farm_volunteering.data.FarmInfoData
import com.youth.farm_volunteering.data.FriendInfoData
import com.youth.farm_volunteering.data.NhInfoData

class DetailTabAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){

    var introTab : FarmIntroFragment? = null
    var expandTab : ExpandFragment? = null
    var reviewTab : FarmReviewFragment? = null
    var tabCount : Int = 0
    var nhIdx : Int = 0
    var getNhInfo : NhInfoData? = null
    var getFriendsInfo: ArrayList<FriendInfoData>? = null
    var getFarmerInfo: FarmInfoData? = null
    var getScheduleInfo : ArrayList<DetailSchData>? = null

    constructor(fm: FragmentManager, tabCount : Int, nhIdx : Int, getNhInfo : NhInfoData,
                getFriendsInfo : ArrayList<FriendInfoData>, getScheduleInfo : ArrayList<DetailSchData>, getFarmerInfo : FarmInfoData) : this(fm)
    {
        this.introTab = FarmIntroFragment()
        this.expandTab = ExpandFragment()
        this.reviewTab = FarmReviewFragment()
        this.tabCount = tabCount
        this.nhIdx = nhIdx
        this.getNhInfo = getNhInfo
        this.getFriendsInfo = getFriendsInfo
        this.getScheduleInfo = getScheduleInfo
        this.getFarmerInfo = getFarmerInfo
    }

    var detailTabDataList : ArrayList<DetailTabData>? = ArrayList()

    fun addFragment(tabText : String, fragment : Fragment){
        detailTabDataList!!.add(DetailTabData(tabText, fragment))
    }

    fun getDetailTabDataList(position : Int) : DetailTabData {
        return detailTabDataList!!.get(position)
    }

    override fun getItem(position: Int): Fragment? {
        when(position){
            0->{
                val bundle = Bundle()
                bundle.putParcelable("nhInfo", getNhInfo)
                bundle.putParcelableArrayList("friendsInfo", getFriendsInfo)
                bundle.putParcelable("farmerInfo", getFarmerInfo)
                bundle.putParcelableArrayList("scheduleInfo", getScheduleInfo)
                introTab!!.arguments = bundle

                return introTab!!
            }
            1->{
                val bundle = Bundle()
                bundle.putInt("nhIdx", nhIdx)
                expandTab!!.arguments = bundle
                return expandTab!!
            }
            2->{
                val bundle = Bundle()
                bundle.putInt("nhIdx", nhIdx)
                reviewTab!!.arguments = bundle
                return reviewTab!!
            }
        }
        return null
    }

    override fun getCount(): Int = tabCount

}