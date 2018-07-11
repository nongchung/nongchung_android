//package com.youth.farm_volunteering.Home.FarmerFrofile
//
//import android.content.Intent
//import android.graphics.PorterDuff
//import android.graphics.drawable.Drawable
//import android.os.Build
//import android.os.Bundle
//import android.support.annotation.RequiresApi
//import android.support.design.widget.TabLayout
//import android.support.v4.app.Fragment
//import android.support.v4.app.FragmentTransaction
//import android.support.v4.view.ViewPager
//import android.support.v7.app.AppCompatActivity
//import android.support.v7.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.Menu
//import android.view.MenuItem
//import android.view.View
//import com.youth.farm_volunteering.Bookmark.LikeFragment
//import com.youth.farm_volunteering.Home.SearchFragment
//import com.youth.farm_volunteering.HomeFragment
//import com.youth.farm_volunteering.MyPage.MypageFragment
//import com.youth.farm_volunteering.R
//import com.youth.farm_volunteering.UndefinedFragment
//import kotlinx.android.synthetic.main.activity_main.*
//import org.sopt.cocochart.client.Main.TabAdapter
//import java.util.ArrayList
//
//
//class FarmerProfileActivity : AppCompatActivity() {
//        var toolbar : android.support.v7.widget.Toolbar? = null
//        var nongHwalTab : View? = null
//        var reviewTab : View? = null
//        var pictureTab : View? = null
//        var tabImage_Array : ArrayList<View>? = null
//        var fragment_Array : ArrayList<Fragment>? = null
//
//        private var linearLayoutManager : LinearLayoutManager? = null
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_farmer_profile)
//
//        var tabAdapter = TabAdapter(supportFragmentManager)
//
//        nongHwalTab = LayoutInflater.from(this).inflate(R.layout.fragment_profile_nonghwal,null,false)
//        reviewTab = LayoutInflater.from(this).inflate(R.layout.fragment_profile_review,null,false)
//        pictureTab = LayoutInflater.from(this).inflate(R.layout.fragment_profile_picture,null,false)
//
//
//        tabImage_Array = arrayListOf(nongHwalTab!!, reviewTab!!, pictureTab!!)     //tab에 들어갈 커스텀뷰들을 array에 넣음
//        fragment_Array = arrayListOf(FragmentProfileNongHwal(), FragmentProfileReview(), FragmentProfilePicture())
//
//        for(i in 0..fragment_Array!!.size-1) {
//            farmer_profile_tab.addTab(farmer_profile_tab.newTab())        //프레그먼트 갯수만큼 탭 생성
//        }
//
//        supportFragmentManager.beginTransaction().add(R.id.farmer_profile_frame, FragmentProfileNongHwal()).commit()        //첫 화면의 container를 HomeFragment()로 설정
//
//        for(i in 0..tabImage_Array!!.size-1) {
//            tabAdapter.addFragment(tabImage_Array!![i], fragment_Array!![i])        //프레그먼트에 맞는 탭의 TabData를 넣음
//        }
//        for(i in 0..tabAdapter.count-1) farmer_profile_tab.getTabAt(i)!!.setCustomView(tabAdapter.getTabDataList(i).tabView) // 탭에 커스텀뷰 설정
//        farmer_profile_tab.setSelectedTabIndicatorHeight(6)
//
//
////        activity_main_tabViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(activity_main_bottomTabLayout))
////
////        activity_main_tabViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
////            override fun onPageScrollStateChanged(state: Int) {
////
////            }
////
////            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
////
////            }
////
////            override fun onPageSelected(position: Int) {
////
////            }
////
////        })
////
////        activity_main_bottomTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
////            override fun onTabReselected(tab: TabLayout.Tab?) {
////
////            }
////
////            override fun onTabUnselected(tab: TabLayout.Tab?) {
////
////            }
////
////            override fun onTabSelected(tab: TabLayout.Tab?) {
//////                activity_main_tabViewPager.currentItem = tab!!.position
////                setCurrentTabFragment(tab!!.position)
////            }
////
////        })
//    }
//
//
//
//
//
//    fun ReplaceFragment(fragment : Fragment){
//        val fm = supportFragmentManager
//        val transaction = fm.beginTransaction()
////        fragment.arguments = bundle
//        transaction.replace(R.id.farmer_profile_frame, fragment)
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
////        transaction.addToBackStack(null)
//        transaction.commit()
//    }
//
//
//    fun setCurrentTabFragment(tabPosition: Int) {
//        when (tabPosition) {
//            tabPosition -> ReplaceFragment(fragment_Array!![tabPosition])
//        }
//    }
//}
