package com.youth.farm_volunteering

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.youth.farm_volunteering.Home.DetailTabAdapter
import com.youth.farm_volunteering.Home.FarmIntroFragment
import com.youth.farm_volunteering.Home.FarmReviewFragment
import com.youth.farm_volunteering.R.id.*
import com.youth.farm_volunteering.data.DetailApplyData
import com.youth.farm_volunteering.data.WeekNonghwalData
import kotlinx.android.synthetic.main.activity_farm_detail.*


class FarmDetailActivity : AppCompatActivity(), View.OnClickListener, OnMapReadyCallback {

    lateinit var applyitems: ArrayList<DetailApplyData>
    lateinit var detailApplyAdapter: DetailApplyAdapter
    private lateinit var mMap: GoogleMap
    //    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var toolbar: android.support.v7.widget.Toolbar? = null

    var fragment_Array: ArrayList<Fragment>? = null
    var tabtextArray : ArrayList<String>? = null
    var detailTabAdapter = DetailTabAdapter(supportFragmentManager)

//    activity_main_tabViewPager.adapter = tabAdapter
//    activity_main_bottomTabLayout.setupWithViewPager(activity_main_tabViewPager)


//    lateinit var scheduleitems: ArrayList<ScheduleData>
//    lateinit var recycleItems: ArrayList<FarmRecyData>
//    lateinit var recycleAdapter: FarmRecyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farm_detail)
        var populData = intent.getParcelableExtra<WeekNonghwalData>("populData")
        var nhIdx : Int = populData.nhIdx!!
        var detailTabAdapter = DetailTabAdapter(supportFragmentManager)


        tabtextArray = arrayListOf("농활소개", "Q & A", "후기")
        fragment_Array = arrayListOf(FarmIntroFragment(), FarmIntroFragment(), FarmReviewFragment())

        for (i in 0..fragment_Array!!.size - 1) {
            tablayoutDetailActivity.addTab(tablayoutDetailActivity.newTab())        //프레그먼트 갯수만큼 탭 생성
        }

        for (i in 0..fragment_Array!!.size - 1) {
            detailTabAdapter!!.addFragment(tabtextArray!![i], fragment_Array!![i])        //프레그먼트에 맞는 탭의 TabData를 넣음
        }

        viewpagerDetailBottom.adapter = detailTabAdapter
        tablayoutDetailActivity.setupWithViewPager(viewpagerDetailBottom)

        for (i in 0..detailTabAdapter.count - 1) tablayoutDetailActivity.getTabAt(i)!!.setText(detailTabAdapter.getDetailTabDataList(i).tabText) // 탭에 커스텀뷰 설정

        viewpagerDetailBottom.setCurrentItem(0)

        applyitems = ArrayList()
        applyitems.add(DetailApplyData("2018년 6월 29일 ~ 30일", "오전 9시 출발 (1박 2일)", "참석가능", "(06명 남음)"))
        applyitems.add(DetailApplyData("2018년 7월 29일 ~ 30일", "오전 10시 출발 (1박 2일)", "참석가능", "(07명 남음)"))
        applyitems.add(DetailApplyData("2018년 8월 29일 ~ 30일", "오전 8시 출발 (1박 2일)", "참석가능", "(05명 남음)"))

        detailApplyAdapter = DetailApplyAdapter(applyitems)
//        detail_apply_rv.layoutManager = LinearLayoutManager(this)
//        detail_apply_rv.adapter = detailApplyAdapter
//
//
//        if(intent.getStringExtra("date") == null){
//            detail_date_btn.setText(applyitems[0].apply_rv_schedule)
//        }
//        else {
//            detail_date_btn.setText(intent.getStringExtra("date"))
//        }
//
//        val mapFragment = supportFragmentManager
//                .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
//
//        recycleItems = ArrayList()
//        recycleItems.add(FarmRecyData(R.drawable.image_1,  "1박2일", "농활", "서울", "20000"))
//        recycleItems.add(FarmRecyData(R.drawable.image_1,  "1박2일", "농활", "서울", "20000"))
//        recycleItems.add(FarmRecyData(R.drawable.image_1,  "1박2일", "농활", "서울", "20000"))
//        recycleItems.add(FarmRecyData(R.drawable.image_1,  "1박2일", "농활", "서울", "20000"))
//        recycleItems.add(FarmRecyData(R.drawable.image_1,  "1박2일", "농활", "서울", "20000"))
//
//        recycleAdapter = FarmRecyAdapter(recycleItems)
//        recycleAdapter.setOnItemClickListener(this)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)    //뒤로가기버튼생성


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar!!.setTitleTextColor(0xFF000000.toInt())
        toolbar!!.title = " "

        Glide.with(applicationContext)
                .load(populData.img)
                .into(imageviewCollapse);
//        detail_location_tv.setText(intent.getStringExtra("farm_location"))
//        detail_name_tv.setText(intent.getStringExtra("farm_name"))
//        detail_price_tv.setText(intent.getIntExtra("farm_price", 0).toString())
//        detail_days_tv.setText(intent.getStringExtra("farm_days"))
//
//        detail_date_btn.setOnClickListener {
//            if (detail_apply_rv.visibility == View.GONE) {
//                detail_black.visibility = View.VISIBLE
//                detail_apply_rv.visibility = View.VISIBLE
//                detail_nsv.isVerticalScrollBarEnabled = false
//            } else if (detail_apply_rv.visibility == View.VISIBLE) {
//                detail_black.visibility = View.GONE
//                detail_apply_rv.visibility = View.GONE
//            }
//        }



//        detail_apply_btn.setOnClickListener{
//            Toast.makeText(applicationContext, "신청버튼 누름", Toast.LENGTH_SHORT).show()
//        }

        viewpagerDetailBottom.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayoutDetailActivity))

        viewpagerDetailBottom.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

        })

        tablayoutDetailActivity.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpagerDetailBottom.currentItem = tab!!.position
            }

        })

    }


    override fun onClick(v: View?) {

        when (v) {


//            farm_introduce -> {
//                clearSelected()
//                farm_introduce.isSelected = true
//                replaceFragment(FarmIntroFragment())
//
//            }
//            farm_location -> {
//                clearSelected()
//                farm_location.isSelected = true
//
//                replaceFragment(ExpandFragment())    //MapsFragment()로 바꿔서 띄우고 싶은데 잘안됩니다...
//
////                var mapFragment = FarmFAQFragment() ;
////                mapFragment.getMapAsync(this)
//
//            }
//            farm_review -> {
//                clearSelected()
//                farm_review.isSelected = true
//                replaceFragment(FarmReviewFragment())
//            }
//

        }

        //따로 스캐줄에서 더 화면을 구성한다면!!!
//        val intent : Intent = Intent(applicationContext,TestActivity::class.java)
//        startActivity(intent)

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val myPlace = LatLng(37.498728, 127.028814)
        mMap.addMarker(MarkerOptions().position(myPlace).title("농활 장소"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 15.0f))
        mMap.uiSettings.isZoomControlsEnabled = true
    }

    // 뒤로가기 함수

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var menuInflater = getMenuInflater()
        menuInflater!!.inflate(R.menu.menu_farmdetail, menu)

        var bookmark: Drawable = menu!!.getItem(0).icon
        bookmark.setColorFilter(0xFFFFFFFF.toInt(), PorterDuff.Mode.MULTIPLY)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_detail_bookmark -> {
//                val intent = Intent(applicationContext, FarmDetailActivity::class.java)
//                startActivity(intent)
            }
        }

        return false
    }

    fun addFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
//        transaction.add(R.id.detail_frame, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        val bundle = Bundle()
//        transaction.replace(R.id.detail_frame, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }
//
//    fun clearSelected() {
//        farm_introduce.isSelected = false
//        farm_location.isSelected = false
//        farm_review.isSelected = false
//    }

}
