package com.youth.farm_volunteering

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpager.LoopingViewPager
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.asksira.loopingviewpagerdemo.DemoInfiniteAdapter
import com.youth.farm_volunteering.Home.IntroThemeFarmAdapter
import com.youth.farm_volunteering.Home.NewFarmAdapter
import com.youth.farm_volunteering.Home.PopulFarmAdapter
import com.youth.farm_volunteering.data.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class HomeFragment : Fragment(), View.OnClickListener {

    var weekFarmAdapter: WeekFarmAdapter? = null
    var introThemeFarmAdapter : IntroThemeFarmAdapter? = null
    var newFarmAdapter : NewFarmAdapter? = null
    var populFarmAdapter : PopulFarmAdapter? = null

    var vpAdapter: DemoInfiniteAdapter? = null
    var adViewPager: LoopingViewPager? = null
    //    var adViewPagerAdapter: AdViewPagerAdapter? = null
    var popularWeekNonghwalList: ArrayList<WeekNonghwalData>? = null
    var newNonghwalList : List<NewNonghwalData>? = null
    var popularFarmList : List<PopulFarmData>? = null
    var detailThemeFarmList: List<DetailThemeFarmData>? = null
    var introThemeFarmList : List<Int>? = null
    var adDataList : List<AdData>? = null

    //    var adViewPager : ViewPager? = null
    var slideImages: ArrayList<Int> = arrayListOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4)

    var horizontalItemDecoration : DividerItemDecoration? = null
    var horizontalDecoration : Drawable? = null

    private var weeklyHotFarm_linearLayoutManager: LinearLayoutManager? = null
    private var newFarm_linearLayoutManager: LinearLayoutManager? = null
    private var themeFarm_linearLayoutManager: LinearLayoutManager? = null
    private var hotFarm_linearLayoutManager: LinearLayoutManager? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_home, container, false)
//        val v_ = inflater!!.inflate(R.layout.fragment_showall, container, false)

        adViewPager = v.findViewById(R.id.fragment_home_adViewPager)


        introThemeFarmList = listOf(R.drawable.main_banner1, R.drawable.main_banner2,
        R.drawable.main_banner3, R.drawable.main_banner4, R.drawable.main_banner5)

        var homeCall = ApplicationController.instance!!.networkService!!.home(); // 서버에서 데이터 가져오는거!!
        homeCall.enqueue(object : Callback<HomeResponseData> {
            override fun onFailure(call: Call<HomeResponseData>, t: Throwable?) {
                Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<HomeResponseData>, response: Response<HomeResponseData>) {
                if(response.isSuccessful){
                    if(response.body().message.equals("Success To Get Information")){
                        adDataList = response.body().ads
                        popularWeekNonghwalList = response.body().populNh
                        newNonghwalList = response.body().newNh
                        popularFarmList = response.body().populFarm

                        weekFarmAdapter = WeekFarmAdapter(popularWeekNonghwalList!!)
                        introThemeFarmAdapter = IntroThemeFarmAdapter(introThemeFarmList!!)
                        newFarmAdapter = NewFarmAdapter(newNonghwalList!!)
                        populFarmAdapter = PopulFarmAdapter(popularFarmList!!)
                        vpAdapter = DemoInfiniteAdapter(activity.applicationContext, adDataList!!, true)


                        fragment_home_weeklyHotFarm_rv.adapter = weekFarmAdapter
                        fragment_home_newFarm_rv.adapter = newFarmAdapter
                        fragment_home_themeFarm_rv.adapter = introThemeFarmAdapter
                        fragment_home_hotFarm_rv.adapter = populFarmAdapter
                        adViewPager!!.adapter = vpAdapter

                    }
                }


            }
        })


//        tabAdapter = this.activity_main_tabViewPager.adapter




//        timer.schedule(adTimerTask, 2000)


//        v.fragment_home_weekendHotFarm.setOnItemClickListener(object : AdapterView.OnItemClickListener{
//            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                var farmItem : WeekFarmData = farmGridAdapter!!.getItem(position) as WeekFarmData
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

    override fun onClick(v: View) {



        when (v) {
            fragment_home_weeklyHotFarm_showAll_txt -> {
                activity!!.main_title.setText("이번 주 인기 농활")
                replaceFragment(ShowAllFragment())
            }
            fragment_home_newFarm_showAll_txt -> {
                activity!!.main_title.setText("새로운 농활")

                replaceFragment(ShowAllFragment())
            }


//            fragment_home_themeFarm_showAll_txt -> {
//                activity!!.main_title.setText("테마별 농활")
//                replaceFragment(ThemaAllFragment())
//            }
//            fragment_home_hotFarm_showAll_txt -> {
//                activity!!.main_title.setText("인기 농장")
//                replaceFragment(ShowAllFragment())
//            }
            // 테마별 농활 인기농장부분 모두보기는 없애기로 함
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_home_weeklyHotFarm_showAll_txt.setOnClickListener(this)
        fragment_home_newFarm_showAll_txt.setOnClickListener(this)

//        fragment_home_themeFarm_showAll_txt.setOnClickListener(this)
//        fragment_home_hotFarm_showAll_txt.setOnClickListener(this)
        //모두보기 테마별과 인기농활에서는 모두보기 없애기로 했습니다


        fragment_home_weeklyHotFarm_rv.layoutManager = LinearLayoutManager(context)
        fragment_home_newFarm_rv.layoutManager = LinearLayoutManager(context)
        fragment_home_themeFarm_rv.layoutManager = LinearLayoutManager(context)
        fragment_home_hotFarm_rv.layoutManager = LinearLayoutManager(context)


        weeklyHotFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        newFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        themeFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        hotFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        fragment_home_weeklyHotFarm_rv!!.setLayoutManager(weeklyHotFarm_linearLayoutManager)
        fragment_home_newFarm_rv!!.setLayoutManager(newFarm_linearLayoutManager)
        fragment_home_themeFarm_rv!!.setLayoutManager(themeFarm_linearLayoutManager)
        fragment_home_hotFarm_rv!!.setLayoutManager(hotFarm_linearLayoutManager)

        //RecyclerView별 horizontal로 간격띄우기
        horizontalItemDecoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        horizontalDecoration = ContextCompat.getDrawable(activity, R.drawable.horizontal_divider)
        horizontalItemDecoration!!.setDrawable(horizontalDecoration!!)

        fragment_home_weeklyHotFarm_rv.addItemDecoration(horizontalItemDecoration)
        fragment_home_newFarm_rv.addItemDecoration(horizontalItemDecoration)
        fragment_home_themeFarm_rv.addItemDecoration(horizontalItemDecoration)
        fragment_home_hotFarm_rv.addItemDecoration(horizontalItemDecoration)

    }

    fun replaceFragment(fragment: Fragment) {
        //FragmentManager는 액티비티만 가질 수 있음, 따라서 MainTab과 같은 Fragment에서는 activity!!.supportFragmentManager 이렇게 호출해줘야 함
        val fm = activity!!.supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.activity_main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        private val title = "title"
        fun newInstance(param1 : String) : HomeFragment{
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(title, param1)
            fragment.arguments = args
            return fragment
        }
    }

}