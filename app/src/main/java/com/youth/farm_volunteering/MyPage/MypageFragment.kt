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
import kotlinx.android.synthetic.main.fragment_mypage.*
import android.content.Intent
import com.youth.farm_volunteering.R.id.imageView
import android.provider.MediaStore
import android.graphics.Bitmap
import android.app.Activity
import android.content.Context
import android.util.Log
import android.provider.MediaStore.Images
import com.youth.farm_volunteering.login.LoginActivity
import com.youth.farm_volunteering.data.MyPageData
import com.youth.farm_volunteering.data.MyPageResponseData
import com.youth.farm_volunteering.login.LoginToken
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import kotlinx.android.synthetic.main.fragment_mypage_1.*
import kotlinx.android.synthetic.main.fragment_mypage_1.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.FileNotFoundException
import java.io.IOException


class MypageFragment : Fragment() {
    private var REQ_CODE_SELECT_IMAGE = 100

    var myPageData: MyPageData? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_mypage_1, container, false)

        if (LoginToken.logined) {

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
        } else {
            var i = Intent(activity, LoginActivity::class.java)
            startActivity(i)
        }
        //내 정보 프레그먼트 밑에 있는 계정, 설정, 지원 전부 다 ImageView로 박은다음에 토글 키가 있는 설정은 RelativeLayout으로 두고 match_parent를 가지는
        //ImageView의 background를 '푸시알림'으로 두고 토글키를 오른쪽 끝에다가 alignRight해주자

        //프로필 사진 변경
        v.imageview_mypage_profile.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
            intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
        })

        //닉네임 변경
        v.nickname_change_button.setOnClickListener(View.OnClickListener {
            var v = Intent(this.context, ChangeNicknameActivity::class.java)
            startActivity(v)
        })
        //비밀번호 변경
        v.password_change_button.setOnClickListener(View.OnClickListener {
            var v = Intent(this.context, ChangePasswordActivity::class.java)
            startActivity(v)
        })

        //푸쉬알림설정
        v.push_button.setOnClickListener(View.OnClickListener {
            var v = Intent(this.context, PushActivity::class.java)
            startActivity(v)
        })
        v.notice_button.setOnClickListener(View.OnClickListener {
            var v = Intent(this.context, NoticeActivity::class.java)
            startActivity(v)
        })
        v.faq_button.setOnClickListener(View.OnClickListener {
            var v = Intent(this.context, FaqActivity::class.java)
            startActivity(v)
        })


//        v.button_mypage_logout.setOnClickListener {
//            LoginToken.token = null
//            var sharedPreference = activity.getSharedPreferences(LoginToken.PREF_KEY, Context.MODE_PRIVATE);
//            var editor = sharedPreference.edit();
//            editor.remove(LoginToken.PREF_KEY)
//            editor.commit()
//            Toast.makeText(activity!!, "로그아웃에 성공하였습니다.", Toast.LENGTH_SHORT).show()
//
//        }
        return v
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.activity.contentResolver, data!!.data)
                    imageview_mypage_profile.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Log.e("test", e.message)
                }
            }
        }
    }

    fun invalidate() {
        Glide.with(activity)
                .load(myPageData!!.img)
                .into(imageview_mypage_profile)
        textview_mypage_email.setText(myPageData!!.mail)
        textview_mypage_nickname.setText(myPageData!!.name)
    }
}