package com.youth.farm_volunteering

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.RequestManager
import com.youth.farm_volunteering.data.HomeResponseData
import com.youth.farm_volunteering.data.NonghwalData
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_showall.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ShowAllFragment : Fragment() {
    //lateinit var showAllAdapter : ShowAllAdapter
    //lateinit var requestManager : RequestManager


    var popularNonghwalList: List<NonghwalData>? = null
    lateinit var farmAdapter: FarmAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_showall, container, false)

//        var farmList : ArrayList<NonghwalData>? = null
//
//        farmList = ArrayList()
//
//        farmAdapter = FarmAdapter(farmList!!)

        var homeCall = ApplicationController.instance!!.networkService!!.home();
        homeCall.enqueue(object : Callback<HomeResponseData> {
            override fun onFailure(call: Call<HomeResponseData>, t: Throwable?) {
                Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<HomeResponseData>, response: Response<HomeResponseData>) {

                popularNonghwalList = response.body().populNh


                farmAdapter = FarmAdapter(popularNonghwalList!!)

                fragment_showall_rv.layoutManager = LinearLayoutManager(context)
                fragment_showall_rv.adapter = farmAdapter

            }
        })

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        fragment_showall_rv.layoutManager = LinearLayoutManager(context)
//
//        fragment_showall_rv.adapter = farmAdapter



    }
}
