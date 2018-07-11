package com.youth.farm_volunteering.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.NickNameResponseData
import kotlinx.android.synthetic.main.fragment_mypage_nickname.*
import kotlinx.android.synthetic.main.fragment_mypage_nickname.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NicknameFragment : Fragment() {

    val changeNick: String = "닉네임" //변수지정


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val v = inflater!!.inflate(R.layout.fragment_mypage_nickname, container, false)

        var nicknameCall = ApplicationController.instance!!.networkService!!.nickname(changeNick)
        //Log.d("aaa",detailnongwalCall.toString())


        nicknameCall.enqueue(object : Callback<NickNameResponseData> {
            override fun onFailure(call: Call<NickNameResponseData>, t: Throwable?) {
                Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
                //Log.e("abc",t.toString())
            }

            override fun onResponse(call: Call<NickNameResponseData>, response: Response<NickNameResponseData>) {

//                ReviewList = response.body().rvListInfo
//
//                v.review_rv.layoutManager = LinearLayoutManager(context)
//                v.review_rv.adapter = reviewAdapter


            }
        })
        //변경 버튼
        v.nickname_change_btn.setOnClickListener {

        }

        return v
    }

}