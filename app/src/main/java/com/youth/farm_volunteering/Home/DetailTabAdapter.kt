package com.youth.farm_volunteering.Home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.youth.farm_volunteering.Expanded.ExpandFragment

class DetailTabAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){

    var introTab : FarmIntroFragment? = null
    var expandTab : ExpandFragment? = null
    var reviewTab : FarmReviewFragment? = null

    constructor(fm: FragmentManager, tabCount : Int) : this(fm){

    }

    var detailTabDataList : ArrayList<DetailTabData>? = ArrayList()

    fun addFragment(tabText : String, fragment : Fragment){
        detailTabDataList!!.add(DetailTabData(tabText, fragment))
    }

    fun getDetailTabDataList(position : Int) : DetailTabData {
        return detailTabDataList!!.get(position)
    }

    override fun getItem(position: Int): Fragment {
        return detailTabDataList!!.get(position).fragment
    }

    override fun getCount(): Int = detailTabDataList!!.size

}