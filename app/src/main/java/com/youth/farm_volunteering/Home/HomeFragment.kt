package com.youth.farm_volunteering

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.asksira.loopingviewpager.LoopingViewPager
import com.asksira.loopingviewpagerdemo.DemoInfiniteAdapter
import com.youth.farm_volunteering.Home.FarmRecyAdapter
import com.youth.farm_volunteering.Home.FarmRecyData
import kotlinx.android.synthetic.main.activity_recycle_list.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : AppCompatActivity(), View.OnClickListener {

    lateinit var recycleItems: ArrayList<FarmRecyData>
    lateinit var recycleAdapter: FarmRecyAdapter

    //가로 조정
    private var linearLayoutManager : LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_list)
        recycleItems = ArrayList()
        recycleItems.add(FarmRecyData(R.drawable.image_1, "1박2일", "농활", "서울", "20000"))

        recycleAdapter = FarmRecyAdapter(recycleItems)
        recycleAdapter.setOnItemClickListener(this)

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = recycleAdapter

        recycleView!!.setLayoutManager(linearLayoutManager)

    }
    override fun onClick(v: View?) {
        //val idx : Int = recycleView.getChildAdapterPosition(v)
        //val name : String = recycleItems[idx].farmtitle
        //val profile : Int = recycleItems[idx].farmprofile
        val intent : Intent = Intent(applicationContext, FarmDetailActivity::class.java)
        startActivity(intent)
    }
}

/*
var vpAdapter: DemoInfiniteAdapter? = null
var adViewPager: LoopingViewPager? = null
//    var adViewPagerAdapter: AdViewPagerAdapter? = null
var farmList : ArrayList<FarmGridData>? = null
//    var adViewPager : ViewPager? = null
var farmGridAdapter : FarmGridAdapter? = null
var slideImages : ArrayList<Int> = arrayListOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4)

override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
val v = inflater!!.inflate(R.layout.fragment_home, container, false)

//        tabAdapter = this.activity_main_tabViewPager.adapter

//        adViewPager = v.findViewById(R.id.fragment_home_adViewPager)
//        adViewPagerAdapter = AdViewPagerAdapter(this.context)
//        adViewPager!!.adapter = adViewPagerAdapter

adViewPager = v.findViewById(R.id.fragment_home_adViewPager)
vpAdapter = DemoInfiniteAdapter(this.context, slideImages, true)
adViewPager!!.adapter = vpAdapter



//        timer.schedule(adTimerTask, 2000)

farmList = ArrayList()

farmList!!.add(FarmGridData(R.drawable.image_1, R.drawable.todays_pick, "1서울 회기","("+"14000"+"원, ","1박"+")","딸기농장"
        , 142.toString()))
farmList!!.add(FarmGridData(R.drawable.image_2, R.drawable.todays_pick, "2서울 합정","("+"14000"+"원, ","1박"+")","사과농장"
        , 142.toString()))
farmList!!.add(FarmGridData(R.drawable.image_3, R.drawable.todays_pick, "3서울 홍대","("+"14000"+"원, ","1박"+")","배농장"
        , 142.toString()))
farmList!!.add(FarmGridData(R.drawable.image_4, R.drawable.todays_pick, "4서울 신촌","("+"14000"+"원, ","1박"+")","감귤농장"
        , 142.toString()))
farmList!!.add(FarmGridData(R.drawable.image_5, R.drawable.todays_pick, "5서울 노원","("+"14000"+"원, ","1박"+")","메론농장"
        , 142.toString()))
farmList!!.add(FarmGridData(R.drawable.image_6, R.drawable.todays_pick, "6서울 강남","("+"14000"+"원, ","1박"+")","레몬농장"
        , 142.toString()))

farmGridAdapter = FarmGridAdapter(farmList!!)
v.fragment_home_weekendHotFarm.adapter = farmGridAdapter

v.fragment_home_weekendHotFarm.setOnItemClickListener(object : AdapterView.OnItemClickListener{
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var farmItem : FarmGridData = farmGridAdapter!!.getItem(position) as FarmGridData
        Toast.makeText(context,""+ farmItem.farmName+ "선택", Toast.LENGTH_SHORT).show()

        val FarmDetailIntent = Intent(context, FarmDetailActivity::class.java)
        FarmDetailIntent.putExtra("farm_img",farmItem.farmPicture)
        FarmDetailIntent.putExtra("farm_location",farmItem.farmLocation)
        FarmDetailIntent.putExtra("farm_name",farmItem.farmName)
        FarmDetailIntent.putExtra("farm_price",farmItem.farmPrice)
        FarmDetailIntent.putExtra("farm_days",farmItem.farmDays)
        startActivity(FarmDetailIntent)

    }

})

return v
}

*/
