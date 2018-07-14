package com.youth.farm_volunteering.MyActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.TotalActivityData
import kotlinx.android.synthetic.main.fragment_myactivity.*
import kotlinx.android.synthetic.main.fragment_myactivity.view.*
import retrofit2.Response
import java.util.*


class MyActivityFragment : Fragment() {

    var myList: List<MyActivityData>? = null
    var timeList: TotalActivityData? = null
    lateinit var myAdapter: MyactivityAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_myactivity, container, false)

        myList = ArrayList()

        var homeCall = ApplicationController.instance!!.networkService!!.myactivity()
        homeCall.enqueue(object : retrofit2.Callback<MyActivityResponseData> {
            override fun onFailure(call: retrofit2.Call<MyActivityResponseData>, t: Throwable?) {
                Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: retrofit2.Call<MyActivityResponseData>, response: Response<MyActivityResponseData>) {
                if (response!!.isSuccessful) {
                    if (response!!.body().message == "success to show activity") {
                        myList = response.body().data
                        timeList = response.body().total

                        myAdapter = MyactivityAdapter(myList!!)
                        farm_count_cases.setText(timeList!!.tcount.toString())
                        farm_count_time.setText(timeList!!.ttime.toString())


                        v.my_rv.layoutManager = LinearLayoutManager(context)
                        v.my_rv.adapter = myAdapter

                    }
                }
            }
        })

        return v
    }
}