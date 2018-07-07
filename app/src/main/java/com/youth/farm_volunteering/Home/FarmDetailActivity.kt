package com.youth.farm_volunteering

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.youth.farm_volunteering.Home.*
import com.youth.farm_volunteering.Home.QandA.qandaFragment
import com.youth.farm_volunteering.R.id.detail_nsv
import com.youth.farm_volunteering.data.ApplyRvData
import junit.framework.Test

import kotlinx.android.synthetic.main.activity_farm_detail.*
import java.util.*


class FarmDetailActivity : AppCompatActivity(), View.OnClickListener, OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var toolbar: android.support.v7.widget.Toolbar? = null
    lateinit var scheduleitems: ArrayList<ScheduleData>
    lateinit var applyitems: ArrayList<ApplyRvData>
    lateinit var scheduleAdapter: ScheduleAdapter
    lateinit var applyAdapter: ApplyRvAdapter

//
//    lateinit var recycleItems: ArrayList<FarmRecyData>
//    lateinit var recycleAdapter: FarmRecyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farm_detail)
//            setContentView(R.layout.item_schedule)      //스캐줄 list 출력


        scheduleitems = ArrayList()
        scheduleitems.add(ScheduleData("서울", "경기", "인천"))
        scheduleitems.add(ScheduleData("서울", "경기", "인천"))
        scheduleitems.add(ScheduleData("서울", "경기", "인천"))

        scheduleAdapter = ScheduleAdapter(scheduleitems)
        scheduleAdapter.setOnItemClickListener(this)

        scheduleView.layoutManager = LinearLayoutManager(this)
        scheduleView.adapter = scheduleAdapter

        applyitems = ArrayList()
        applyitems.add(ApplyRvData("2018년 6월 29일 ~ 30일", "오전 9시 출발 (1박 2일)", "참석가능", "(06명 남음)"))
        applyitems.add(ApplyRvData("2018년 6월 29일 ~ 30일", "오전 9시 출발 (1박 2일)", "참석가능", "(06명 남음)"))
        applyitems.add(ApplyRvData("2018년 6월 29일 ~ 30일", "오전 9시 출발 (1박 2일)", "참석가능", "(06명 남음)"))
        applyAdapter = ApplyRvAdapter(applyitems)
        detail_apply_rv.layoutManager = LinearLayoutManager(this)
        detail_apply_rv.adapter = applyAdapter





        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        Glide.with(toolbarImage.context)
                .load(intent.getStringExtra("farm_img"))
                .into(toolbarImage);
        detail_location_tv.setText(intent.getStringExtra("farm_location"))
        detail_name_tv.setText(intent.getStringExtra("farm_name"))
        detail_price_tv.setText(intent.getIntExtra("farm_price", 0).toString())
        detail_days_tv.setText(intent.getStringExtra("farm_days"))

        addFragment(FarmIntroFragment())
        farm_introduce.isSelected = true
        farm_introduce.setOnClickListener(this)
        farm_location.setOnClickListener(this)
        farm_review.setOnClickListener(this)


        detail_date_btn.setOnClickListener {
            if (detail_apply_rv.visibility == View.GONE) {
                detail_black.visibility = View.VISIBLE
                detail_apply_rv.visibility = View.VISIBLE
            } else if (detail_apply_rv.visibility == View.VISIBLE) {
                detail_black.visibility = View.GONE
                detail_apply_rv.visibility = View.GONE
            }
        }

        detail_black.setOnClickListener{
            if(detail_black.visibility == View.VISIBLE){
                detail_black.visibility = View.GONE
                detail_apply_rv.visibility = View.GONE
            }
        }

        detail_apply_btn.setOnClickListener{
            Toast.makeText(applicationContext, "신청버튼 누름", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClick(v: View?) {
        when (v) {
            farm_introduce -> {
                clearSelected()
                farm_introduce.isSelected = true
                replaceFragment(FarmIntroFragment())
            }
            farm_location -> {
                clearSelected()
                farm_location.isSelected = true

                replaceFragment(FarmFAQFragment())    //MapsFragment()로 바꿔서 띄우고 싶은데 잘안됩니다...

//                var mapFragment = FarmFAQFragment() ;
                replaceFragment(FarmFAQFragment())
//                mapFragment.getMapAsync(this)

            }
            farm_review -> {
                clearSelected()
                farm_review.isSelected = true
                replaceFragment(FarmReviewFragment())
            }
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


    fun clickFloat() {

    }

    fun addFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.detail_frame, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.detail_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun clearSelected() {
        farm_introduce.isSelected = false
        farm_location.isSelected = false
        farm_review.isSelected = false
    }


}
