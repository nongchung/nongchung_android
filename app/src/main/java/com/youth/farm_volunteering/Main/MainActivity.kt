package com.youth.farm_volunteering.Main

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
import com.youth.farm_volunteering.Bookmark.LikeFragment
import com.youth.farm_volunteering.Home.SearchFragment
import com.youth.farm_volunteering.HomeFragment
import com.youth.farm_volunteering.MyActivity.MyActivityFragment
import com.youth.farm_volunteering.MyPage.MypageFragment
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_review_write.view.*
import org.sopt.cocochart.client.Main.TabAdapter
import android.view.KeyEvent



class MainActivity : AppCompatActivity() {

    var toolbar: android.support.v7.widget.Toolbar? = null
    var homeTab: View? = null
    var bookmarklistTab: View? = null
    var mypageTab: View? = null
    var mylogTab: View? = null
    var searchTab: View? = null
    var tabImage_Array: ArrayList<View>? = ArrayList()
    var fragment_Array: ArrayList<Fragment>? = ArrayList()

    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tabAdapter = TabAdapter(supportFragmentManager)

        activity_main_tabViewPager.adapter = tabAdapter
        activity_main_bottomTabLayout.setupWithViewPager(activity_main_tabViewPager)

        toolbar = findViewById(R.id.activity_main_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.hide()




        homeTab = LayoutInflater.from(this).inflate(R.layout.tab_home, null, false)
        bookmarklistTab = LayoutInflater.from(this).inflate(R.layout.tab_bookmarklist, null, false)
        mypageTab = LayoutInflater.from(this).inflate(R.layout.tab_mypage, null, false)
        mylogTab = LayoutInflater.from(this).inflate(R.layout.tab_mylog, null, false)
        searchTab = LayoutInflater.from(this).inflate(R.layout.tab_search, null, false)

        tabImage_Array = arrayListOf(homeTab!!, searchTab!!, mylogTab!!, bookmarklistTab!!, mypageTab!!)     //tab에 들어갈 커스텀뷰들을 array에 넣음

        fragment_Array = arrayListOf(HomeFragment(), SearchFragment(), MyActivityFragment(), LikeFragment(), MypageFragment())

        for (i in 0..fragment_Array!!.size - 1) {
            activity_main_bottomTabLayout.addTab(activity_main_bottomTabLayout.newTab())        //프레그먼트 갯수만큼 탭 생성
        }

        supportFragmentManager.beginTransaction().replace(R.id.activity_main_container, HomeFragment()).commit()        //첫 화면의 container를 HomeFragment()로 설정

        for (i in 0..tabImage_Array!!.size - 1) {
            tabAdapter.addFragment(tabImage_Array!![i], fragment_Array!![i])        //프레그먼트에 맞는 탭의 TabData를 넣음
        }
        for (i in 0..tabAdapter.count - 1) activity_main_bottomTabLayout.getTabAt(i)!!.setCustomView(tabAdapter.getTabDataList(i).tabView) // 탭에 커스텀뷰 설정
        activity_main_bottomTabLayout.setSelectedTabIndicatorHeight(6)

        activity_main_tabViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(activity_main_bottomTabLayout))

        activity_main_tabViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

        })

        activity_main_bottomTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
//                activity_main_tabViewPager.currentItem = tab!!.position
                setCurrentTabFragment(tab!!.position)
            }

        })
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