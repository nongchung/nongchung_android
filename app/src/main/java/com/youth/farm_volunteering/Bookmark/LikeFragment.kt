package com.youth.farm_volunteering.Bookmark

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.LikeData
import com.youth.farm_volunteering.data.LikeResponseData
import kotlinx.android.synthetic.main.fragment_like.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.Activity
import android.widget.FrameLayout
import com.youth.farm_volunteering.HomeFragment
import com.youth.farm_volunteering.Main.MainActivity
import com.youth.farm_volunteering.data.BookmarkData
import kotlinx.android.synthetic.main.activity_main.*


class LikeFragment : Fragment(){

    //Adapter에서의 holder.itemView.setOnClickListener와 fragment에서의 onClick은 동시에 사용할 수 없다.
    //Adapter의 클릭이 우선순위로 잡힘


    var likeList : List<LikeData>? = null



    private var like_linearLayoutManager: LinearLayoutManager? = null

    lateinit var likeAdapter: LikeAdapter
    lateinit var requestManager : RequestManager


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_like, container, false)


        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requestManager = Glide.with(this)

        var likeCall = ApplicationController.instance!!.networkService!!.like(); // 서버에서 데이터 가져오는거!!
        likeCall.enqueue(object : Callback<LikeResponseData> {
            override fun onFailure(call: Call<LikeResponseData>, t: Throwable?) {
                Toast.makeText(activity, "like request fail", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<LikeResponseData>, response: Response<LikeResponseData>) {
                if(response!!.isSuccessful){
                    if(response!!.body().message == "Success"){
                        likeList = response.body().bmList
                        likeAdapter = LikeAdapter(likeList!!)





                        fragment_like_rv.adapter = likeAdapter


                        //layoutManager 안 달아주면 list가 뜨지 않어 ㅜㅜ 이거 몰라서 디지는 줄 알았자너 ㅜㅜ 멍청이였어
                        fragment_like_rv.layoutManager = LinearLayoutManager(context)
                        like_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        fragment_like_rv!!.setLayoutManager(like_linearLayoutManager)


                    }
                }
            }
        })





    }
}