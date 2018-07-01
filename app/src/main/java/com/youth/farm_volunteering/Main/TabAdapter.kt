package org.sopt.cocochart.client.Main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View


class TabAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){

    var tabDataList : ArrayList<TabData>? = ArrayList()

    fun addFragment(tabView : View, fragment : Fragment){
        tabDataList!!.add(TabData(tabView, fragment))
    }

    fun getTabDataList(position : Int) : TabData {
        return tabDataList!!.get(position)
    }

    override fun getItem(position: Int): Fragment{
        return tabDataList!!.get(position).fragment
    }

    override fun getCount(): Int = tabDataList!!.size


}