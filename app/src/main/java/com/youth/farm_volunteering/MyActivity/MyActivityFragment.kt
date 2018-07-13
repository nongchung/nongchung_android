package com.youth.farm_volunteering.MyActivity

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.NewFarmAllAdapter
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.farm_count_cases
import com.youth.farm_volunteering.data.MyActivityData
import com.youth.farm_volunteering.data.MyActivityResponseData
import com.youth.farm_volunteering.data.TotalActivityData
import kotlinx.android.synthetic.main.fragment_myactivity.*
import kotlinx.android.synthetic.main.fragment_myactivity.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyActivityFragment : Fragment() {

    var myList: List<MyActivityData>? = null
    var timeList : List<TotalActivityData>? = null
    lateinit var myAdapter: MyactivityAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_myactivity, container, false)

//        var farmList : ArrayList<WeekNonghwalData>? = null
//
//        farmList = ArrayList()
//
//        weekFarmAdapter = WeekFarmAdapter(farmList!!)

        var homeCall = ApplicationController.instance!!.networkService!!.myactivity()
        homeCall.enqueue(object : Callback<MyActivityResponseData> {
            override fun onFailure(call: Call<MyActivityResponseData>, t: Throwable?) {
                Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MyActivityResponseData>, response: Response<MyActivityResponseData>) {

                myList = response.body().data
                timeList = response.body().total

                myAdapter = MyactivityAdapter(myList!!)

                farm_count_cases.setText(timeList!![0].tcount.toString())
                farm_count_time.setText(timeList!![0].ttime.toString())

                v.my_rv.layoutManager = LinearLayoutManager(context)
                v.my_rv.adapter = myAdapter
            }
        })
        return v
    }
}