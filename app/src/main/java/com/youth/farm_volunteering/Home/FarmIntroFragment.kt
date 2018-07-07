package com.youth.farm_volunteering.Home

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.*
import com.youth.farm_volunteering.data.*
import kotlinx.android.synthetic.main.fragment_farm_introduce.*
import kotlinx.android.synthetic.main.fragment_farm_introduce.view.*
import kotlinx.android.synthetic.main.layout_group.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FarmIntroFragment : Fragment() {

    lateinit var friendinfoAdapter: FriendInfoAdapter
    lateinit var scheduleAdapter: ScheduleAdapter

    var DetailNonghwalList:NhInfoData? = null
    var DetailFriendInfoList: List<FriendInfoData>? = null
    var DetailFarmInfoList: FarmInfoData? = null
    var DetailScheduleList: List<DetailSchData>? = null


    private var introduceImage_linearLayoutManager: LinearLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_farm_introduce, container, false)
//        activity!!.supportFragmentManager.beginTransaction().add()

        var detailnongwalCall  = ApplicationController.instance!!.networkService!!.detailnonghwal(2)
        Log.d("aaa",detailnongwalCall.toString())

        detailnongwalCall.enqueue(object : Callback<DetailNonghwalResponseData> {
            override fun onFailure(call: Call<DetailNonghwalResponseData>, t: Throwable?) {
                Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
                Log.e("abc",t.toString())
            }
            override fun onResponse(call: Call<DetailNonghwalResponseData>, response: Response<DetailNonghwalResponseData>) {
                DetailNonghwalList = response.body().nhInfo
                DetailFriendInfoList = response.body().friendsInfo
                DetailFarmInfoList = response.body().farmerInfo
                DetailScheduleList = response.body().schedule

                detail_introduce_addr.setText(DetailNonghwalList!!.addr.toString())
                detail_introduce_name.setText(DetailNonghwalList!!.name.toString())
//                setText(DetailNonghwalList!!.star.toString())
                detail_introduce_star.rating = DetailNonghwalList!!.star!!.toFloat() //rating에 서버에서 float값 받아와서 생성
                detail_introduce_description.setText(DetailNonghwalList!!.description.toString())
                detail_introduce_price.setText(DetailNonghwalList!!.price.toString())
                detail_introduce_period.setText(DetailNonghwalList!!.period.toString())

                friendinfoAdapter =FriendInfoAdapter(DetailFriendInfoList!!)

                v.friendinfoView_rv.layoutManager = LinearLayoutManager(context)
                v.friendinfoView_rv.adapter = friendinfoAdapter

                introduceImage_linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

                v.friendinfoView_rv!!.setLayoutManager(introduceImage_linearLayoutManager)

                farminfo_name.setText(DetailFarmInfoList!!.name.toString())
                farminfo_comment.setText(DetailFarmInfoList!!.comment.toString())

                scheduleAdapter = ScheduleAdapter(DetailScheduleList!!)

                v.scheduleView_rv.layoutManager = LinearLayoutManager(context)
                v.scheduleView_rv.adapter = scheduleAdapter

            }
        })

        return v


    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        IntroduceImageView_rv.layoutManager = LinearLayoutManager(context)
////
//        introduceImage_linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//
//        IntroduceImageView_rv!!.setLayoutManager(introduceImage_linearLayoutManager)}


}


