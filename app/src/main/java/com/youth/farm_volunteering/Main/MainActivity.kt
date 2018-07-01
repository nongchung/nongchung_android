package com.youth.farm_volunteering.Main

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.youth.farm_volunteering.*
import com.youth.farm_volunteering.Bookmark.LikeFragment
import com.youth.farm_volunteering.MyPage.MypageFragment
import com.youth.farm_volunteering.SignUp.SignupActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.sopt.cocochart.client.Main.TabAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    var toolbar : android.support.v7.widget.Toolbar? = null
    var homeTab : View? = null
    var likeTab : View? = null
    var mypageTab : View? = null
    var undefinedTab : View? = null
    var tabImage_Array : ArrayList<View>? = null
    var fragment_Array : ArrayList<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tabAdapter = TabAdapter(supportFragmentManager)

        toolbar = findViewById(R.id.activity_main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//        supportActionBar!!.setHomeAsUpIndicator(R.drawable.side_menu)

        homeTab = LayoutInflater.from(this).inflate(R.layout.tab_home,null,false)
        likeTab = LayoutInflater.from(this).inflate(R.layout.tab_like,null,false)
        mypageTab = LayoutInflater.from(this).inflate(R.layout.tab_mypage,null,false)
        undefinedTab = LayoutInflater.from(this).inflate(R.layout.tab_undefined,null,false)

        tabImage_Array = arrayListOf(homeTab!!, likeTab!!, mypageTab!!, undefinedTab!!)     //tab에 들어갈 커스텀뷰들을 array에 넣음
        fragment_Array = arrayListOf(HomeFragment(), LikeFragment(), MypageFragment(), UndefinedFragment())


        for(i in 0..fragment_Array!!.size-1) {
            activity_main_bottomTabLayout.addTab(activity_main_bottomTabLayout.newTab())        //프레그먼트 갯수만큼 탭 생성
        }

        supportFragmentManager.beginTransaction().add(R.id.activity_main_container, HomeFragment()).commit()        //첫 화면의 container를 HomeFragment()로 설정

        for(i in 0..tabImage_Array!!.size-1) {
            tabAdapter.addFragment(tabImage_Array!![i], fragment_Array!![i])        //프레그먼트에 맞는 탭의 TabData를 넣음
        }
        for(i in 0..tabAdapter.count-1) activity_main_bottomTabLayout.getTabAt(i)!!.setCustomView(tabAdapter.getTabDataList(i).tabView) // 탭에 커스텀뷰 설정
        activity_main_bottomTabLayout.setSelectedTabIndicatorHeight(6)

        activity_main_bottomTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var menuInflater = getMenuInflater()
        menuInflater!!.inflate(R.menu.menu_main, menu)

        var searchIcon : Drawable = menu!!.getItem(0).icon
        searchIcon.setColorFilter(0xFF000000.toInt(), PorterDuff.Mode.MULTIPLY)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_search_icon -> {
                val intent = Intent(applicationContext, SignupActivity::class.java)
                startActivity(intent)
            }
        }

        return false
    }


    fun ReplaceFragment(fragment : Fragment){
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
            tabPosition -> ReplaceFragment(fragment_Array!![tabPosition])
        }
    }
}
