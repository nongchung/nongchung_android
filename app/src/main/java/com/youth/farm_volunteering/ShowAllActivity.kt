package com.youth.farm_volunteering

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.data.HomeNonghwalData
import com.youth.farm_volunteering.data.HomeResponseData
import kotlinx.android.synthetic.main.activity_show_all.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShowAllActivity : AppCompatActivity() {

    var popularHomeNonghwalList: List<HomeNonghwalData>? = null
    lateinit var weekFarmAdapter: WeekFarmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all)


        var homeCall = ApplicationController.instance!!.networkService!!.home();
        homeCall.enqueue(object : Callback<HomeResponseData> {
            override fun onFailure(call: Call<HomeResponseData>, t: Throwable?) {
                Toast.makeText(this@ShowAllActivity, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<HomeResponseData>, response: Response<HomeResponseData>) {

                popularHomeNonghwalList = response.body().populNh

                weekFarmAdapter = WeekFarmAdapter(popularHomeNonghwalList!!)

                activity_show_all_rv.layoutManager = LinearLayoutManager(this@ShowAllActivity)
                activity_show_all_rv.adapter = weekFarmAdapter

            }

        })
    }
}