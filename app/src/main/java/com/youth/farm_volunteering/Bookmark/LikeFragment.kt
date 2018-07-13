package com.youth.farm_volunteering.Bookmark

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
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
import java.util.*

class LikeFragment : Fragment() {

    var likeList : List<LikeData>? = null

    private var like_linearLayoutManager: LinearLayoutManager? = null

    lateinit var likeAdapter: LikeAdapter
    lateinit var requestManager : RequestManager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_like, container, false)

        requestManager = Glide.with(this)

        var likeCall = ApplicationController.instance!!.networkService!!.like(); // 서버에서 데이터 가져오는거!!
        likeCall.enqueue(object : Callback<LikeResponseData> {
            override fun onFailure(call: Call<LikeResponseData>, t: Throwable?) {
                Toast.makeText(activity, "like request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LikeResponseData>, response: Response<LikeResponseData>) {

                likeList = response.body().bmList
                likeAdapter = LikeAdapter(likeList!!)
                fragment_like_rv.adapter = likeAdapter

                //layoutManager 안 달아주면 list가 뜨지 않어 ㅜㅜ 이거 몰라서 디지는 줄 알았자너 ㅜㅜ 멍청이였어
                fragment_like_rv.layoutManager = LinearLayoutManager(context)
                like_linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                fragment_like_rv!!.setLayoutManager(like_linearLayoutManager)

            }
        })




//        likeList = ArrayList()
//
//        likeList.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
//        likeList.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
//        likeList.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
//        likeList.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
//        likeList.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
//        likeList.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
//        likeList.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
//
//        likeAdapter = LikeAdapter(likeList!!)

        return v
    }

}