package com.youth.farm_volunteering.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.FarmAdapter
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.MyPageData
import com.youth.farm_volunteering.data.MyPageResponseData
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MypageFragment : Fragment() {
    var myPageData: MyPageData? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_mypage, container, false)


        var mypageCall = ApplicationController.instance!!.networkService!!.mypage();
        mypageCall.enqueue(object : Callback<MyPageResponseData> {
            override fun onFailure(call: Call<MyPageResponseData>, t: Throwable?) {
                Toast.makeText(activity!!, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MyPageResponseData>, response: Response<MyPageResponseData>) {

                myPageData = response.body().data!!.get(0)
                invalidate()
            }
        })
        //내 정보 프레그먼트 밑에 있는 계정, 설정, 지원 전부 다 ImageView로 박은다음에 토글 키가 있는 설정은 RelativeLayout으로 두고 match_parent를 가지는
        //ImageView의 background를 '푸시알림'으로 두고 토글키를 오른쪽 끝에다가 alignRight해주자
        return v
    }

    fun invalidate() {
        Glide.with(activity)
                .load(myPageData!!.img)
                .into(imageview_mypage_profile);
        textview_mypage_email.setText(myPageData!!.mail)
        textview_mypage_nickname.setText(myPageData!!.name)


    }
}