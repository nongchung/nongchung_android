package com.youth.farm_volunteering.home.FarmProfile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.youth.farm_volunteering.R

import com.youth.farm_volunteering.data.*
import kotlinx.android.synthetic.main.activity_farm_detail.*
import kotlinx.android.synthetic.main.activity_fram_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FarmProfileActivity : AppCompatActivity() {

    var profileNonghwalList: List<ProfileFarmerInfoData>? = null

    lateinit var profileViewList: List<FarmerProfileData>
    lateinit var profileViewAdapter: FarmProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fram_profile)

        var farmIdx = intent.getIntExtra("farmIdx", 0)
//


        var profileCall = ApplicationController.instance!!.networkService!!.farmprofile(farmIdx)
        profileCall.enqueue(object : Callback<FarmProfileResponseData> {

            override fun onFailure(call: Call<FarmProfileResponseData>, t: Throwable?) {
                Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<FarmProfileResponseData>, response: Response<FarmProfileResponseData>) {
                profileNonghwalList = response.body().farmerInfo
                profileViewList = response.body().nhInfo!!


//        profileViewList = ArrayList()
//        profileViewList.add(FarmerProfileData(20000, "제주 행복 감귤 농장", "1박2일", R.drawable.main_img1,"제주도 서귀포시"))
//        profileViewList.add(FarmerProfileData(44000, "행복 가득 딸기 농장", "2박3일", R.drawable.main_img2, "충청남도 논산시"))
//        profileViewList.add(FarmerProfileData(35000, "감귤 재배 농장", "당일치기", R.drawable.main_img4,"제주도 서귀포시"))

                profileViewAdapter = FarmProfileAdapter(profileViewList)


                Glide.with(applicationContext)
                        .load(profileNonghwalList!![0].farmerImg)
                        .into(profile_userimage)
                
                profile_farm_name.setText(profileNonghwalList!![0].farmName)
                profile_address.setText(profileNonghwalList!![0].farmAddr)
                farmer_profile_name.setText(profileNonghwalList!![0].farmerName)
                farmer_profile_phone.setText(profileNonghwalList!![0].farmerPhone)
                farmer_profile_comment.setText(profileNonghwalList!![0].farmerComment)

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