package com.youth.farm_volunteering

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asksira.loopingviewpager.LoopingViewPager
import com.asksira.loopingviewpagerdemo.DemoInfiniteAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*



class HomeFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View) {
        when(v){
            fragment_home_weeklyHotFarm_showAll_txt -> {
                replaceFragment(ShowAllFragment())
            }
            fragment_home_newFarm_showAll_txt -> {
                replaceFragment(ShowAllFragment())
            }
            fragment_home_themeFarm_showAll_txt -> {
                replaceFragment(ShowAllFragment())
            }
            fragment_home_hotFarm_showAll_txt -> {
                replaceFragment(ShowAllFragment())
            }
        }
    }

    lateinit var farmAdapter: FarmAdapter
    var vpAdapter: DemoInfiniteAdapter? = null
    var adViewPager: LoopingViewPager? = null
    //    var adViewPagerAdapter: AdViewPagerAdapter? = null
    var farmList : ArrayList<FarmData>? = null
    //    var adViewPager : ViewPager? = null
    var slideImages : ArrayList<Int> = arrayListOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4)


    private var weeklyHotFarm_linearLayoutManager : LinearLayoutManager? = null
    private var newFarm_linearLayoutManager : LinearLayoutManager? = null
    private var themeFarm_linearLayoutManager : LinearLayoutManager? = null
    private var hotFarm_linearLayoutManager : LinearLayoutManager? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_home, container, false)

//        tabAdapter = this.activity_main_tabViewPager.adapter

        adViewPager = v.findViewById(R.id.fragment_home_adViewPager)

        vpAdapter = DemoInfiniteAdapter(this.context, slideImages, true)
        adViewPager!!.adapter = vpAdapter

//        timer.schedule(adTimerTask, 2000)

        farmList = ArrayList()

        farmList!!.add(FarmData(R.drawable.image_1, R.drawable.todays_pick, "1서울 회기","("+"14000"+"원, ","1박"+")","딸기농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_2, R.drawable.todays_pick, "2서울 합정","("+"14000"+"원, ","1박"+")","사과농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_3, R.drawable.todays_pick, "3서울 홍대","("+"14000"+"원, ","1박"+")","배농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_4, R.drawable.todays_pick, "4서울 신촌","("+"14000"+"원, ","1박"+")","감귤농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_5, R.drawable.todays_pick, "5서울 노원","("+"14000"+"원, ","1박"+")","메론농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_6, R.drawable.todays_pick, "6서울 강남","("+"14000"+"원, ","1박"+")","레몬농장"
                , 142.toString()))

        farmAdapter = FarmAdapter(farmList!!)



//        v.fragment_home_weekendHotFarm.setOnItemClickListener(object : AdapterView.OnItemClickListener{
//            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                var farmItem : FarmData = farmGridAdapter!!.getItem(position) as FarmData
//                Toast.makeText(context,""+ farmItem.farmName+ "선택", Toast.LENGTH_SHORT).show()
//
//                val FarmDetailIntent = Intent(context, FarmDetailActivity::class.java)
//                FarmDetailIntent.putExtra("farm_img",farmItem.farmPicture)
//                FarmDetailIntent.putExtra("farm_location",farmItem.farmLocation)
//                FarmDetailIntent.putExtra("farm_name",farmItem.farmName)
//                FarmDetailIntent.putExtra("farm_price",farmItem.farmPrice)
//                FarmDetailIntent.putExtra("farm_days",farmItem.farmDays)
//                startActivity(FarmDetailIntent)
//
//            }

//        })

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_home_weeklyHotFarm_showAll_txt.setOnClickListener(this)
        fragment_home_newFarm_showAll_txt.setOnClickListener(this)
        fragment_home_themeFarm_showAll_txt.setOnClickListener(this)
        fragment_home_hotFarm_showAll_txt.setOnClickListener(this)


        fragment_home_weeklyHotFarm_rv.layoutManager = LinearLayoutManager(context)
        fragment_home_newFarm_rv.layoutManager = LinearLayoutManager(context)
        fragment_home_themeFarm_rv.layoutManager = LinearLayoutManager(context)
        fragment_home_hotFarm_rv.layoutManager = LinearLayoutManager(context)

        fragment_home_weeklyHotFarm_rv.adapter = farmAdapter
        fragment_home_newFarm_rv.adapter = farmAdapter
        fragment_home_themeFarm_rv.adapter = farmAdapter
        fragment_home_hotFarm_rv.adapter = farmAdapter

        weeklyHotFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        newFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        themeFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        hotFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)

        fragment_home_weeklyHotFarm_rv!!.setLayoutManager(weeklyHotFarm_linearLayoutManager)
        fragment_home_newFarm_rv!!.setLayoutManager(newFarm_linearLayoutManager)
        fragment_home_themeFarm_rv!!.setLayoutManager(themeFarm_linearLayoutManager)
        fragment_home_hotFarm_rv!!.setLayoutManager(hotFarm_linearLayoutManager)

    }

    fun replaceFragment(fragment: Fragment) {
        //FragmentManager는 액티비티만 가질 수 있음, 따라서 MainTab과 같은 Fragment에서는 activity!!.supportFragmentManager 이렇게 호출해줘야 함
        val fm = activity!!.supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.activity_main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}