package com.youth.farm_volunteering.Home.FarmProfile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.Home.WeekFarmAllAdapter
import com.youth.farm_volunteering.NewFarmAllAdapter
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.*
import com.youth.farm_volunteering.data.*
import kotlinx.android.synthetic.main.activity_fram_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FarmProfileActivity : AppCompatActivity() {

    var profileNonghwalList : ProfileFarmerInfoData? = null
    var profileViewList : List<FarmerProfileData>? = null
    lateinit var profileViewAdapter: FarmProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fram_profile)


        var profileCall = ApplicationController.instance!!.networkService!!.farmprofile(5)
        profileCall.enqueue(object : Callback<FarmProfileResponseData> {
            override fun onFailure(call: Call<FarmProfileResponseData>, t: Throwable?) {
                Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<FarmProfileResponseData>, response: Response<FarmProfileResponseData>) {
                profileNonghwalList = response.body().farmerInfo
                profileViewList = response.body().nhInfo


                profileViewAdapter = FarmProfileAdapter(profileViewList!!)

                profile_farm_name.setText(profileNonghwalList!!.farmName)
                profile_address.setText(profileNonghwalList!!.farmAddr)
                farmer_profile_name.setText(profileNonghwalList!!.farmerName)
                farmer_profile_phone.setText(profileNonghwalList!!.farmerPhone)
                farmer_profile_comment.setText(profileNonghwalList!!.farmerComment)

                profile_rv.layoutManager = LinearLayoutManager(this@FarmProfileActivity)
                profile_rv.adapter = profileViewAdapter


//                newNonghwalList = response.body().data
//
//
//                newNonghwalAdapter = NewFarmAllAdapter(newNonghwalList!!)
//
//                fragment_showall_rv.layoutManager = LinearLayoutManager(context)
//                fragment_showall_rv.adapter = newNonghwalAdapter

            }
        })


    }
}