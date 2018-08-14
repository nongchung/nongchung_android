package org.sopt.cocochart.client.Main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import com.youth.farm_volunteering.HomeFragment
import com.youth.farm_volunteering.MyPage.MypageFragment
import com.youth.farm_volunteering.bookmark.LikeFragment
import com.youth.farm_volunteering.home.SearchFragment
import com.youth.farm_volunteering.myactivity.MyActivityFragment

class TabAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm){

    var tabCustomViewList : ArrayList<View>? = ArrayList()

    var tabCount : Int = 0;

    var homeTab : HomeFragment? = null
    var searchTab : SearchFragment? = null
    var mylogTab : MyActivityFragment? = null
    var bookmarkListTab : LikeFragment? = null
    var mypageTab : MypageFragment? = null

    constructor(fm: FragmentManager, tabCount : Int, tabCustomViewList : ArrayList<View>) : this(fm){
        this.tabCount = tabCount
        this.homeTab = HomeFragment()
        this.searchTab = SearchFragment()
        this.mylogTab = MyActivityFragment()
        this.bookmarkListTab = LikeFragment()
        this.mypageTab = MypageFragment()
        this.tabCustomViewList = tabCustomViewList
    }

    fun addCustomeView(tabView : View){
        tabCustomViewList!!.add(tabView)
    }

    fun getCustomeViewList(position : Int) : View {
        return tabCustomViewList!!.get(position)
    }

    override fun getItem(position: Int): Fragment?{
        when(position){
            0->{
                val bundle = Bundle()

                homeTab!!.arguments = bundle
                return homeTab
            }
            1->{
                val bundle = Bundle()

                searchTab!!.arguments = bundle
                return searchTab
            }
            2->{
                val bundle = Bundle()

                mylogTab!!.arguments = bundle
                return mylogTab
            }
            3->{
                val bundle = Bundle()

                bookmarkListTab!!.arguments = bundle
                return bookmarkListTab
            }
            4->{
                val bundle = Bundle()

                mypageTab!!.arguments = bundle
                return mypageTab
            }
        }
        return null
    }

    override fun getCount(): Int = tabCount

}