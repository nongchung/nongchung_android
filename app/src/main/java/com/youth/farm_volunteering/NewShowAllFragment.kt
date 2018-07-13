package com.youth.farm_volunteering

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.Home.NewFarmAdapter
import com.youth.farm_volunteering.data.AllNewData
import com.youth.farm_volunteering.data.AllNewResponseData
import com.youth.farm_volunteering.data.HomeResponseData
import com.youth.farm_volunteering.data.NewNonghwalData
import kotlinx.android.synthetic.main.activity_show_all.*
import kotlinx.android.synthetic.main.fragment_showall.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewShowAllFragment : Fragment() {
    var newNonghwalList : List<AllNewData>? = null
    lateinit var newNonghwalAdapter: NewFarmAllAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_showall, container, false)

//        var farmList : ArrayList<WeekNonghwalData>? = null
//
//        farmList = ArrayList()
//
//        weekFarmAdapter = WeekFarmAdapter(farmList!!)

        var homeCall = ApplicationController.instance!!.networkService!!.new(6)
        homeCall.enqueue(object : Callback<AllNewResponseData> {
            override fun onFailure(call: Call<AllNewResponseData>, t: Throwable?) {
                Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<AllNewResponseData>, response: Response<AllNewResponseData>) {

                newNonghwalList = response.body().data


                newNonghwalAdapter = NewFarmAllAdapter(newNonghwalList!!)

                fragment_showall_rv.layoutManager = LinearLayoutManager(context)
                fragment_showall_rv.adapter = newNonghwalAdapter

            }
        })

        return v
    }

}