package com.youth.farm_volunteering

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpager.LoopingViewPager
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.asksira.loopingviewpagerdemo.DemoInfiniteAdapter
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.home.IntroThemeFarmAdapter
import com.youth.farm_volunteering.home.NewFarmAdapter
import com.youth.farm_volunteering.home.PopulFarmAdapter
import com.youth.farm_volunteering.data.*
import com.youth.farm_volunteering.login.LoginToken
import com.youth.farm_volunteering.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class HomeFragment : Fragment(), View.OnClickListener {

    private val ARG_PARAM1 = "이번 주 인기 농활"
    private val ARG_PARAM2 = "새로운 농활"

    var mParam: String? = null

    var weekFarmAdapter: WeekFarmAdapter? = null
    var introThemeFarmAdapter : IntroThemeFarmAdapter? = null
    var newFarmAdapter : NewFarmAdapter? = null
    var populFarmAdapter : PopulFarmAdapter? = null

    var vpAdapter: DemoInfiniteAdapter? = null
    var adViewPager: LoopingViewPager? = null
    //    var adViewPagerAdapter: AdViewPagerAdapter? = null
    var popularHomeNonghwalList: List<HomeNonghwalData>? = null
    var newNonghwalList : List<HomeNonghwalData>? = null
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

        adViewPager = v.findViewById(R.id.fragment_home_adViewPager)

        introThemeFarmList = listOf(R.drawable.main_banner1, R.drawable.main_banner2,
        R.drawable.main_banner3, R.drawable.main_banner4, R.drawable.main_banner5)

        var homeCall = ApplicationController.instance!!.networkService!!.home(); // 서버에서 데이터 가져오는거!!
        homeCall.enqueue(object : Callback<HomeResponseData> {
            override fun onFailure(call: Call<HomeResponseData>, t: Throwable?) {
                Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<HomeResponseData>, response: Response<HomeResponseData>) {
                if(response.code()==200) {
                    if (response.body().message.equals("Success To Get Information")) {
                        Log.v("Tababab", response.body().populNh!![0].toString())
                        adDataList = response.body().ads
                        popularHomeNonghwalList = response.body().populNh
                        newNonghwalList = response.body().newNh
                        popularFarmList = response.body().populFarm

                        weekFarmAdapter = WeekFarmAdapter(popularHomeNonghwalList!!)    //클리어
                        introThemeFarmAdapter = IntroThemeFarmAdapter(introThemeFarmList!!, fragmentManager)
                        introThemeFarmAdapter!!.setOnItemClickListener(this@HomeFragment)
                        newFarmAdapter = NewFarmAdapter(newNonghwalList!!)          //클리어
                        vpAdapter = DemoInfiniteAdapter(activity!!.applicationContext, adDataList!!, true)

                        fragment_home_weeklyHotFarm_rv.adapter = weekFarmAdapter
                        fragment_home_newFarm_rv.adapter = newFarmAdapter
                        fragment_home_themeFarm_rv.adapter = introThemeFarmAdapter
//                        fragment_home_hotFarm_rv.adapter = populFarmAdapter 인기농장 뺌
                        adViewPager!!.adapter = vpAdapter

                    }
                }else if(response.code() == 500){       //토큰이 만료되었을 경우 Local storage에 있는 로그인 정보를 초기화 시켜줌
                    Toast.makeText(activity.applicationContext, "토큰이 만료됨. 다시 로그인해주세요!", Toast.LENGTH_SHORT).show()
                    LoginToken.token = null
                    var sharedPreference = activity.getSharedPreferences(LoginToken.PREF_KEY, Context.MODE_PRIVATE)
                    var editor = sharedPreference.edit()
                    editor.remove(LoginToken.PREF_KEY)
                    LoginToken.logined = false
                    editor.commit()

                    val intent = Intent(activity.applicationContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                    startActivity(intent)

                }
            }
        })

        return v
    }

    override fun onClick(v: View) {

        when (v) {
            fragment_home_weeklyHotFarm_showAll_txt -> {
                val intent = Intent(this.activity.applicationContext, ShowAllActivity::class.java)
                intent.putExtra("title", "이번 주 인기농활")
                startActivity(intent)

//                val fragment = ShowAllFragment()
//                val args = Bundle()
//                args.putString("title", "이번 주 인기농활")
//                fragment.setArguments(args)
//                fragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
//                        .addToBackStack(null).add(R.id.activity_main_container, fragment)
//                        .commit()
            }
            fragment_home_newFarm_showAll_txt -> {
                val intent = Intent(this.activity.applicationContext, ShowAllActivity::class.java)
                intent.putExtra("title", "새로운 농활")
                startActivity(intent)

//                val fragment = ShowAllFragment()
//                val args = Bundle()
//                args.putString("title", "새로운 농활")
//                fragment.setArguments(args)
//                fragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
//                        .addToBackStack(null).add(R.id.activity_main_container, fragment)
//                        .commit()
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

        fragment_home_weeklyHotFarm_rv.layoutManager = LinearLayoutManager(context)
        fragment_home_newFarm_rv.layoutManager = LinearLayoutManager(context)
        fragment_home_themeFarm_rv.layoutManager = LinearLayoutManager(context)
//        fragment_home_hotFarm_rv.layoutManager = LinearLayoutManager(context)인기농장 뺌


        weeklyHotFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        newFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        themeFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        hotFarm_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) 인기농장 뺌

        fragment_home_weeklyHotFarm_rv!!.setLayoutManager(weeklyHotFarm_linearLayoutManager)
        fragment_home_newFarm_rv!!.setLayoutManager(newFarm_linearLayoutManager)
        fragment_home_themeFarm_rv!!.setLayoutManager(themeFarm_linearLayoutManager)
//        fragment_home_hotFarm_rv!!.setLayoutManager(hotFarm_linearLayoutManager)  인기농장 뺌

        //RecyclerView별 horizontal로 간격띄우기
        horizontalItemDecoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        horizontalDecoration = ContextCompat.getDrawable(activity, R.drawable.horizontal_divider)
        horizontalItemDecoration!!.setDrawable(horizontalDecoration!!)

        fragment_home_weeklyHotFarm_rv.addItemDecoration(horizontalItemDecoration)
        fragment_home_newFarm_rv.addItemDecoration(horizontalItemDecoration)
        fragment_home_themeFarm_rv.addItemDecoration(horizontalItemDecoration)
//        fragment_home_hotFarm_rv.addItemDecoration(horizontalItemDecoration)인기농장 뺌

    }
    fun replaceFragment(fragment: Fragment) {
        //FragmentManager는 액티비티만 가질 수 있음, 따라서 MainTab과 같은 Fragment에서는 activity!!.supportFragmentManager 이렇게 호출해줘야 함
        val fm = activity!!.supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.activity_main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun setTitle(title: String): ShowAllFragment {
        val f = ShowAllFragment()
        // Pass index input as an argument.
        val args = Bundle()
        args.putString("title", title)
        f.setArguments(args)
        return f
    }

}