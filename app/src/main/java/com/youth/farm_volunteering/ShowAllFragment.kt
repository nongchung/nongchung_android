package com.youth.farm_volunteering

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import kotlinx.android.synthetic.main.fragment_showall.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.youth.farm_volunteering.Home.WeekFarmAllAdapter
import com.youth.farm_volunteering.data.AllNewData
import com.youth.farm_volunteering.data.AllNewResponseData
import com.youth.farm_volunteering.data.PopularResponseData
import com.youth.farm_volunteering.data.PopularSubData


class ShowAllFragment : Fragment() {

    var title : String? = null

    //lateinit var showAllAdapter : ShowAllAdapter
    //lateinit var requestManager : RequestManager

    var popularWeekNonghwalList: List<PopularSubData>? = null
    var allNewNonghwalList: List<AllNewData>? = null

    lateinit var weekFarmAdapter: WeekFarmAllAdapter
    lateinit var newFarmAdapter: NewFarmAllAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_showall, container, false)

        title = this.arguments.getString("title")


//        val title = args.getString("title", "aaa")


//        var farmList : ArrayList<HomeNonghwalData>? = null
//
//        farmList = ArrayList()
//
//        weekFarmAdapter = WeekFarmAdapter(farmList!!)


        if(title == "이번 주 인기농활"){
            var homeCall = ApplicationController.instance!!.networkService!!.popular(6);
            homeCall.enqueue(object : Callback<PopularResponseData> {
                override fun onFailure(call: Call<PopularResponseData>, t: Throwable?) {
                    Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<PopularResponseData>, response: Response<PopularResponseData>) {

                    popularWeekNonghwalList = response.body().data

                    weekFarmAdapter = WeekFarmAllAdapter(popularWeekNonghwalList!!)

                    fragment_showall_rv.layoutManager = LinearLayoutManager(context)
                    fragment_showall_rv.adapter = weekFarmAdapter

                }
            })

            return v
        }
        else if(title == "새로운 농활"){
            var homeCall = ApplicationController.instance!!.networkService!!.new(6);

            homeCall.enqueue(object : Callback<AllNewResponseData> {
                override fun onFailure(call: Call<AllNewResponseData>, t: Throwable?) {
                    Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<AllNewResponseData>, response: Response<AllNewResponseData>) {

                    allNewNonghwalList = response.body().data

                    newFarmAdapter = NewFarmAllAdapter(allNewNonghwalList!!)

                    fragment_showall_rv.layoutManager = LinearLayoutManager(context)
                    fragment_showall_rv.adapter = newFarmAdapter

                }
            })
        }
        return v
    }
    fun replaceFragment(fragment: Fragment) {
        //FragmentManager는 액티비티만 가질 수 있음, 따라서 MainTab과 같은 Fragment에서는 activity!!.supportFragmentManager 이렇게 호출해줘야 함
        val fm = activity!!.supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.activity_main_container, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        show_all_title.setText(title)



    }

}
