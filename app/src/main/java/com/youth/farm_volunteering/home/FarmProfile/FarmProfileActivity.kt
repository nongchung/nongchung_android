package com.youth.farm_volunteering.home.FarmProfile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.youth.farm_volunteering.R

import com.youth.farm_volunteering.data.*
import kotlinx.android.synthetic.main.activity_fram_profile.*

class FarmProfileActivity : AppCompatActivity() {

    var profileNonghwalList: ProfileFarmerInfoData? = null
    lateinit var profileViewList: ArrayList<FarmerProfileData>
    lateinit var profileViewAdapter: FarmProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fram_profile)


//        var profileCall = ApplicationController.instance!!.networkService!!.farmprofile(5)
//        profileCall.enqueue(object : Callback<FarmProfileResponseData> {
//            override fun onFailure(call: Call<FarmProfileResponseData>, t: Throwable?) {
//                Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(call: Call<FarmProfileResponseData>, response: Response<FarmProfileResponseData>) {
//                profileNonghwalList = response.body().farmerInfo
//                profileViewList = response.body().nhInfo

        profileViewList = ArrayList()
        profileViewList.add(FarmerProfileData(20000, "제주 행복 감귤 농장", "1박2일", R.drawable.main_img1,"제주도 서귀포시"))
        profileViewList.add(FarmerProfileData(44000, "행복 가득 딸기 농장", "2박3일", R.drawable.main_img2, "충청남도 논산시"))
        profileViewList.add(FarmerProfileData(35000, "감귤 재배 농장", "당일치기", R.drawable.main_img4,"제주도 서귀포시"))

        profileViewAdapter = FarmProfileAdapter(profileViewList!!)


        profile_userimage.setImageResource(R.drawable.main_img1)
        profile_farm_name.setText("제주 감귤농장")
        profile_address.setText("제주특별자치도 서귀포시")
        farmer_profile_name.setText("김승기 농부")
        farmer_profile_phone.setText("010 6213 8883")
        farmer_profile_comment.setText("언제나 여러분들을 환영합니다\n궁금한 점이 있으면 편하게 연락 주세요 ^^~ㅎㅎㅎㅎ")

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
}