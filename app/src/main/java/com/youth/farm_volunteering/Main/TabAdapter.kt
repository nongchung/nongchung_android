package org.sopt.cocochart.client.Main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import com.youth.farm_volunteering.Bookmark.LikeFragment
import com.youth.farm_volunteering.Home.SearchFragment
import com.youth.farm_volunteering.HomeFragment
import com.youth.farm_volunteering.MyLogFragment
import com.youth.farm_volunteering.MyPage.MypageFragment


class TabAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){

    var tabCount : Int? = null
    var homeTab : HomeFragment? = null
    var searchTab : SearchFragment? = null
    var mylogTab : MyLogFragment? = null
    var bookmarklistTab : LikeFragment? = null
    var mypageTab : MypageFragment? = null
    var tabDataList : ArrayList<TabData> = ArrayList()

    constructor(fm : FragmentManager, tabCount : Int) : this(fm){
        this.tabCount = tabCount
        this.homeTab = HomeFragment()
        this.searchTab = SearchFragment()
        this.mylogTab = MyLogFragment()
        this.bookmarklistTab = LikeFragment()
        this.mypageTab = MypageFragment()
    }

    fun addFragment(tabView : View, fragment : Fragment){
        tabDataList!!.add(TabData(tabView, fragment))
    }

    fun getTabDataList(position : Int) : TabData {
        return tabDataList!!.get(position)
    }

    override fun getItem(position: Int): Fragment{
        when(position){
            0 -> {
                return homeTab!!
            }
            1 -> {
                return searchTab!!
            }
            2 -> {
                return mylogTab!!
            }
            3 -> {
                return bookmarklistTab!!
            }
            4 -> {
                return mypageTab!!
            }
        }
        return tabDataList!!.get(position).fragment
    }

    override fun getCount(): Int = tabCount!!

}