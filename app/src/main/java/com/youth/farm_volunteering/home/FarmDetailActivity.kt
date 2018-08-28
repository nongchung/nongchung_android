package com.youth.farm_volunteering

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.youth.farm_volunteering.expanded.ExpandFragment
import com.youth.farm_volunteering.home.Schedule.DetailSchData
import com.youth.farm_volunteering.home.Schedule.ScheduleAdapter
import com.youth.farm_volunteering.data.*
import com.youth.farm_volunteering.home.*
import com.youth.farm_volunteering.login.LoginToken
import kotlinx.android.synthetic.main.activity_farm_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FarmDetailActivity : AppCompatActivity(), View.OnClickListener, OnMapReadyCallback {

    lateinit var applyList: ArrayList<DetailApplyData>
    lateinit var detailApplyAdapter: DetailApplyAdapter
    private lateinit var mMap: GoogleMap

    //    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var toolbar: Toolbar? = null
    var userDataList: ArrayList<UserData>? = null

    var detailNonghwalList: NhInfoData? = null
    var detailFriendInfoList: ArrayList<FriendInfoData>? = null
    var detailFarmInfoList: FarmInfoData? = null
    lateinit var detailScheduleList: ArrayList<DetailSchData>

    var detailImageList: List<String>? = null
    var detailNearestStartDate: String? = null
    var detailNearestEndDate : String? = null
    lateinit var detailAllStartDate: ArrayList<AllStData>
    var detailMyScheduleActivities: ArrayList<Int> = ArrayList()
    var isSchApplied : Boolean = false
    var applicationCancelDialog : ApplicationCancelDialog? = null
    var scheIdx: Int? = null

    var selectedStData : AllStData? = null

    lateinit var detailTabAdapter: DetailTabAdapter

    var fragment_Array: ArrayList<Fragment>? = ArrayList()
    var tabtextArray: ArrayList<String>? = null

    var bottomSheetDialog: BottomSheetDialog? = null

    var splitDateDay : Int? = null
    var formatTemp : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farm_detail)

        toolbar = findViewById(R.id.toolbarDetail)
        setSupportActionBar(toolbar)    //뒤로가기버튼생성

        var isFromSearch: Boolean = intent.getBooleanExtra("is_from_search", false)
        var isFromShowAll : Boolean = intent.getBooleanExtra("is_from_showall", false)
        var isFromTheme : Boolean = intent.getBooleanExtra("is_from_theme", false)
        var populData: NonghwalData
        var dateFormat = SimpleDateFormat("yyyy-MM-dd")
        var dateDotFormat = SimpleDateFormat("yyyy.MM.dd")

        if (isFromSearch) {
            populData = intent.getParcelableExtra("populData") as NonghwalData
        } else if(isFromShowAll){
            populData = intent.getParcelableExtra("populData") as NonghwalData
        } else if(isFromTheme){
            populData = intent.getParcelableExtra("populData") as NonghwalData
        }
        else {
            populData = intent.getParcelableExtra<HomeNonghwalData>("populData")
        }
        tabtextArray = arrayListOf("농활소개", "Q & A", "후기")
        fragment_Array = arrayListOf(FarmIntroFragment(), ExpandFragment(), FarmReviewFragment())

        for (i in 0..fragment_Array!!.size - 1) {
            tablayoutDetailActivity.addTab(tablayoutDetailActivity.newTab())        //프레그먼트 갯수만큼 탭 생성
        }

        var getDetail = ApplicationController.instance!!.networkService!!.detailnonghwal(populData.getRealId()!!)
        getDetail.enqueue(object : Callback<DetailNonghwalResponseData> {
            override fun onResponse(call: Call<DetailNonghwalResponseData>?, response: Response<DetailNonghwalResponseData>?) {
                if (response!!.isSuccessful) {
                    detailNonghwalList = response.body().nhInfo             //농활소개
                    detailFriendInfoList = response.body().friendsInfo     //농활소개
                    detailFarmInfoList = response.body().farmerInfo        //농활소개
                    detailScheduleList = response.body().schedule!!           //BottomSheetDialog 신청하기
                    detailNearestStartDate = response.body().nearestStartDate!!   //BottomSheetDialog 신청하기
                    detailNearestEndDate = response.body().nearestEndDate!!        //BottomSheetDialog 신청하기
                    detailAllStartDate = response.body().allStartDate!!            //BottomSheetDialog 신청하기
                    if (response.body().myScheduleActivities != null) {
                        detailMyScheduleActivities = response.body().myScheduleActivities!!       //BottomSheetDialog 취소 만들기위한 sche
                    }
                    scheIdx = detailAllStartDate[0].idx
                    selectedStData = detailAllStartDate[0]
                    isApplied(selectedStData!!.idx!!, detailMyScheduleActivities!!)

                    var parsedStartDate : Date = dateFormat.parse(detailNearestStartDate)        //이렇게하면 SimpleDateFormat형 변수인 dateFormat으로
                                                                                                //파싱된 paredDate라는 2018년 07월 21일이라는
                                                                                                //Date형 변수가 얻어진다
                    var parsedEndDate : Date = dateFormat.parse(detailNearestEndDate)
                    var dateFormatStart = SimpleDateFormat("yyyy년 MM월 dd일")
                    var firstStartDate = dateFormatStart.format(parsedStartDate)
                    var dateFormatEnd = SimpleDateFormat(" ~ dd일")
                    var firstEndDate = dateFormatEnd.format(parsedEndDate)

                    formatTemp = firstStartDate + firstEndDate

                    if(detailNonghwalList!!.period!="당일 치기") {          //1박 이상일 때
                        var splitDay: List<String> = detailNonghwalList!!.period!!.split("박")       //ex) 1박 2일을 넣고 1과 2일로 쪼갬
                        var splitDayDay = Integer.parseInt(splitDay[0])                       //     '1'과 '2일' 중에서 1을 Int형으로 저장한 변수
                        var splitDate : List<String> = detailNearestStartDate!!.split("-")      //ex) 2018-07-21
//                      var splitDateYear = Integer.parseInt(splitDate[0])                                      //    2018
//                      var splitDateMonth = Integer.parseInt(splitDate[1])                                     //    07
                        splitDateDay = Integer.parseInt(splitDate[2])                                         //    21
                        var afterDate = splitDateDay!! + splitDayDay

                        buttonApplyDate.text = firstStartDate + firstEndDate
                    }else{
                        buttonApplyDate.text = firstStartDate + firstEndDate
                    }

                    detailTabAdapter = DetailTabAdapter(supportFragmentManager, tablayoutDetailActivity.tabCount, populData.getRealId()!!,
                            detailNonghwalList!!, detailFriendInfoList!!, detailScheduleList!!, detailFarmInfoList!!)


                    for (i in 0..fragment_Array!!.size - 1) {
                        detailTabAdapter!!.addFragment(tabtextArray!![i], fragment_Array!![i])        //프레그먼트에 맞는 탭의 TabData를 넣음
                    }

                    viewpagerDetailBottom.adapter = detailTabAdapter
                    tablayoutDetailActivity.setupWithViewPager(viewpagerDetailBottom)

                    for (i in 0..detailTabAdapter.count - 1) tablayoutDetailActivity.getTabAt(i)!!.setText(detailTabAdapter.getDetailTabDataList(i).tabText) // 탭에 커스텀뷰 설정

                    detailImageList = response.body().image                  //상단 농활이미지
                }

            }

            override fun onFailure(call: Call<DetailNonghwalResponseData>?, t: Throwable?) {
                Toast.makeText(applicationContext, "통신상태를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        })

        val refundListener = View.OnClickListener {
            val requestRefund = ApplicationController.instance!!.networkService!!.requestSchRefund(populData.getRealId()!!, scheIdx!!)
            requestRefund.enqueue(object : Callback<requestRefundResponse>{
                override fun onFailure(call: Call<requestRefundResponse>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "통신상태를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<requestRefundResponse>?, response: Response<requestRefundResponse>?) {
                    if(response!!.isSuccessful){
                        if(response.body().message == "Success To Cancel"){
                            detailMyScheduleActivities = response.body().myScheduleActivities!!
                            isApplied(scheIdx!!, detailMyScheduleActivities!!)
                            Toast.makeText(applicationContext, "취소 완료!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
            applicationCancelDialog!!.dismiss()
        }

        val backToDetailListener = View.OnClickListener {
            applicationCancelDialog!!.dismiss()
        }

        buttonApplyButton.setOnClickListener {
//            if (scheIdx == null) {
//                selectedStData = detailAllStartDate[0]        //progressbar 넣어줘야할듯
//            }
            if(!isSchApplied) {

                var getUserInfo = ApplicationController.instance!!.networkService!!.getUserInfo()
                getUserInfo.enqueue(object : Callback<UserResponseData> {
                    override fun onResponse(call: Call<UserResponseData>?, response: Response<UserResponseData>?) {
                        if (response!!.isSuccessful) {
                            if (response!!.body().message == "Success To Get User Info") {
                                userDataList = response.body().data
                                val intent = Intent(applicationContext, ApplicationActivity::class.java)
                                intent.putParcelableArrayListExtra("userData", userDataList)
                                intent.putExtra("nhIdx", populData!!.getRealId())
                                intent.putExtra("nhName", populData!!.name)
                                intent.putExtra("nhAddr", populData!!.addr)
                                intent.putExtra("nhPrice", populData!!.price)
                                intent.putExtra("nhImg", populData!!.img)
                                intent.putExtra("scheDate", buttonApplyDate.text.toString())
                                intent.putExtra("scheIdx", selectedStData!!.idx)
                                intent.putExtra("selectedStData", selectedStData)
                                intent.putExtra("period", populData.period)
                                startActivityForResult(intent, applyReqCode)
                            }
                        }

                    }

                    override fun onFailure(call: Call<UserResponseData>?, t: Throwable?) {
//
                        Toast.makeText(applicationContext, "Please Check Network", Toast.LENGTH_SHORT).show()
                    }

                })
            } else{

                var parsedStart = dateFormat.parse(selectedStData!!.startDate)
                var parsedEnd = dateFormat.parse(selectedStData!!.endDate)

                var formattedStart = dateDotFormat.format(parsedStart)
                var formattedEnd = dateDotFormat.format(parsedEnd)

                var startArr : List<String> = formattedStart.split(".")
                var EndArr : List<String> = formattedEnd.split(".")

                applicationCancelDialog = ApplicationCancelDialog(this, refundListener, backToDetailListener,
                        populData.addr!!, populData.name!!, startArr[0] + "." + startArr[1], EndArr[0] + "." + EndArr[1],
                        startArr[2], EndArr[2])
                applicationCancelDialog!!.show()
                applicationCancelDialog!!.setOnDismissListener (object : DialogInterface.OnDismissListener{
                    override fun onDismiss(dialog: DialogInterface?) {
                        isApplied(scheIdx!!, detailMyScheduleActivities!!)
                    }
                })
            }

        }

        buttonApplyDate.setOnClickListener {
            bottomSheetDialog = BottomSheetDialog.instance
            val bundle = Bundle()
            bundle.putParcelableArrayList("scheList", detailScheduleList)
            bundle.putIntegerArrayList("myScheduleActivities", detailMyScheduleActivities)
            bundle.putParcelableArrayList("allStartItems", detailAllStartDate)
            bundle.putString("nearestStartDate", detailNearestStartDate)
            var bottomSheetDialog = BottomSheetDialog.instance
            bottomSheetDialog.onDismissListener = DialogInterface.OnDismissListener {
                if (bottomSheetDialog.selectedDate == null) {
                    buttonApplyDate.text = formatTemp
                } else {
                    buttonApplyDate.text = bottomSheetDialog.selectedDate
                }
                if(bottomSheetDialog.selectedStData!= null) {
                    selectedStData = bottomSheetDialog.selectedStData
                    scheIdx = bottomSheetDialog.selectedIdx

                    getChangedFriendList(scheIdx!!)
                }
//                checkSchState(selectedStData!!.state!!)
                isApplied(selectedStData!!.idx!!, detailMyScheduleActivities!!)         //에러 발견
            }

            bottomSheetDialog!!.arguments = bundle
            bottomSheetDialog!!.show(supportFragmentManager, "bottomSheet")
        }

        //탭레이아웃 색상 선택
        tablayoutDetailActivity.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#3470FF"))
        viewpagerDetailBottom.setCurrentItem(0)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar!!.setTitleTextColor(0xFF000000.toInt())
        toolbar!!.title = " "

        Glide.with(applicationContext)
                .load(populData.getRealImg())
                .apply(RequestOptions().placeholder(R.drawable.loading_big_image))
                .into(imageviewCollapse)
        imageviewCollapse.scaleType = ImageView.ScaleType.FIT_XY
        viewpagerDetailBottom.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayoutDetailActivity))

        tablayoutDetailActivity.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewpagerDetailBottom.currentItem = tab!!.position
            }

        })

    }

    fun isApplied(selectedSchIdx : Int, myActivities : List<Int>) {

        if(myActivities.size != 0)
        {
            for (i in 0 until myActivities.size) {
                if (selectedSchIdx == myActivities[i]) {
                    isSchApplied = true
                    buttonApplyButton.text = "취소하기"

                } else {
                    isSchApplied = false
                    buttonApplyButton.text = "신청하기"
                }
            }
        } else{
            isSchApplied = false
            buttonApplyButton.text = "신청하기"
        }
    }

    override fun onClick(v: View?) {

        when (v) {

        }
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


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_detail_bookmark -> {
//                val intent = Intent(applicationContext, FarmDetailActivity::class.java)
//                startActivity(intent)
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == applyReqCode) {
            if(resultCode == Activity.RESULT_OK) {
                Toast.makeText(applicationContext, "신청 완료!", Toast.LENGTH_SHORT).show()
                detailMyScheduleActivities!!.add(scheIdx!!)         //실시간 갱신이 안되니까 전체를 다 다시 불러올 수도 없는 노릇이니 임시방편
                isApplied(scheIdx!!, detailMyScheduleActivities!!)  //갱신된 detailMyScheduleActivities가 필요함
            }
        }

    }

    companion object {
        val applyReqCode = 101;
    }

    fun getChangedFriendList(scheIdx : Int){
        val getChangedFriendList = ApplicationController.instance!!.networkService!!.getChangedFriendList(scheIdx)
        getChangedFriendList.enqueue(object : Callback<FriendInfoResponseData>{
            override fun onFailure(call: Call<FriendInfoResponseData>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Fail to get changed FriendsList!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<FriendInfoResponseData>?, response: Response<FriendInfoResponseData>?) {
                if(response!!.code() == 200){
                    if(response.isSuccessful){
                        detailFriendInfoList = response.body().friendsInfo
//                        detailTabAdapter.getFriendsInfo = response.body().friendsInfo
//                        detailTabAdapter.getItem(0)
                        detailTabAdapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
}
