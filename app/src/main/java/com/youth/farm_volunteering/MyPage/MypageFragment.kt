package com.youth.farm_volunteering.MyPage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.Login.LoginActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.MyPageData
import com.youth.farm_volunteering.data.MyPageResponseData
import com.youth.farm_volunteering.login.LoginToken
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.fragment_mypage.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MypageFragment : Fragment() {
    private var REQ_CODE_SELECT_IMAGE = 100

    var myPageData: MyPageData? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_mypage, container, false)

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
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

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
        v.nickname.setOnClickListener(View.OnClickListener {
            var nick = Intent(this.context, NicknameFragment::class.java)
            startActivity(nick)
        })

        v.button_mypage_logout.setOnClickListener {
            LoginToken.token = null;
            var sharedPreference = activity.getSharedPreferences(LoginToken.PREF_KEY, Context.MODE_PRIVATE);
            var editor = sharedPreference.edit();
            editor.remove(LoginToken.PREF_KEY)
            editor.commit()
            Toast.makeText(activity!!, "로그아웃에 성공하였습니다.", Toast.LENGTH_SHORT).show()

        }

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
                .into(imageview_mypage_profile);
        textview_mypage_email.setText(myPageData!!.mail)
        textview_mypage_nickname.setText(myPageData!!.name)


    }
}