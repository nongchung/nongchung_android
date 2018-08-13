package com.youth.farm_volunteering.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.youth.farm_volunteering.bookmark.LikeFragment
import com.youth.farm_volunteering.home.SearchFragment
import com.youth.farm_volunteering.HomeFragment
import com.youth.farm_volunteering.myactivity.MyActivityFragment
import com.youth.farm_volunteering.mypage.MypageFragment
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_main.*
import org.sopt.cocochart.client.Main.TabAdapter


class MainActivity : AppCompatActivity() {

    var toolbar: android.support.v7.widget.Toolbar? = null
    var homeTab: View? = null
    var bookmarklistTab: View? = null
    var mypageTab: View? = null
    var mylogTab: View? = null
    var searchTab: View? = null
    var tabImageArray: ArrayList<View>? = ArrayList()
    var fragment_Array: ArrayList<Fragment>? = ArrayList()
    var mainTabLayout : TabLayout? = null

    var homeFragment : HomeFragment = HomeFragment()

    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.activity_main_toolbar)
        mainTabLayout = findViewById(R.id.activity_main_bottomTabLayout)
        setSupportActionBar(toolbar)

        homeTab = LayoutInflater.from(this).inflate(R.layout.tab_home, null, false)
        bookmarklistTab = LayoutInflater.from(this).inflate(R.layout.tab_bookmarklist, null, false)
        mypageTab = LayoutInflater.from(this).inflate(R.layout.tab_mypage, null, false)
        mylogTab = LayoutInflater.from(this).inflate(R.layout.tab_mylog, null, false)
        searchTab = LayoutInflater.from(this).inflate(R.layout.tab_search, null, false)

        tabImageArray = arrayListOf(homeTab!!, searchTab!!, mylogTab!!, bookmarklistTab!!, mypageTab!!)     //tab에 들어갈 커스텀뷰들을 array에 넣음

        for (i in 0..tabImageArray!!.size - 1) {
            mainTabLayout!!.addTab(activity_main_bottomTabLayout.newTab())        //프레그먼트 갯수만큼 탭 생성
        }

        val tabAdapter = TabAdapter(supportFragmentManager, mainTabLayout!!.tabCount, tabImageArray!!)

        activity_main_tabViewPager.adapter = tabAdapter
        mainTabLayout!!.setupWithViewPager(activity_main_tabViewPager)

        activity_main_tabViewPager.currentItem = 0
        activity_main_tabViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mainTabLayout!!))

        mainTabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                activity_main_tabViewPager.currentItem = tab!!.position

            }
        })

//        fragment_Array = arrayListOf(HomeFragment(), SearchFragment(), MyActivityFragment(), LikeFragment(), MypageFragment())

//        supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, HomeFragment()).commit()        //첫 화면의 container를 HomeFragment()로 설정

        for (i in 0..tabAdapter.count - 1) activity_main_bottomTabLayout.getTabAt(i)!!.customView = tabAdapter.getCustomeViewList(i) // 탭에 커스텀뷰 설정
        activity_main_bottomTabLayout.setSelectedTabIndicatorHeight(6)
        activity_main_bottomTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#3470FF"))

        activity_main_tabViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(activity_main_bottomTabLayout))

    }

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var menuInflater = getMenuInflater()
        menuInflater!!.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        return false
    }


    fun ReplaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
//        fragment.arguments = bundle
        transaction.replace(R.id.activity_main_container, fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun setCurrentTabFragment(tabPosition: Int) {
        when (tabPosition) {
            tabPosition -> {
                ReplaceFragment(fragment_Array!![tabPosition])
                Log.d("aaa",fragment_Array!![tabPosition].toString())
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()


    }
}