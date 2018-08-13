package com.youth.farm_volunteering

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.main.MainActivity
import com.youth.farm_volunteering.data.LoginResponseData
import com.youth.farm_volunteering.login.LoginData
import com.youth.farm_volunteering.login.LoginToken
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var prototypeEmail : String = "test@test.test"
        var prototypePassword : String = "test"

        var requestLogin = ApplicationController.instance!!.networkService!!.login(prototypeEmail, prototypePassword)
        requestLogin.enqueue(object : Callback<LoginResponseData>{
            override fun onFailure(call: Call<LoginResponseData>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<LoginResponseData>?, response: Response<LoginResponseData>?) {
                var sharedPreference = getSharedPreferences(LoginToken.PREF_KEY, Context.MODE_PRIVATE);
                var editor = sharedPreference.edit();
                editor.putString(LoginToken.PREF_KEY, response!!.body().token)
                editor.putString("uName", response.body().data!![0].name)
                editor.putInt("uPoint", response.body().data!![0].point!!)
                editor.putString("uImg", response.body().data!![0].img)
                editor.putString("uNickname", response.body().data!![0].nickname)
                editor.putInt("uAge", response.body().data!![0].age!!)

                editor.commit()
                LoginToken.token = response.body().token

                LoginData.name = response.body().data!![0].name
                LoginData.point = response.body().data!![0].point
                LoginData.img = response.body().data!![0].img
                LoginData.nickname = response.body().data!![0].nickname
                LoginData.age = response.body().data!![0].age


            }

        })

        val introListener = object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        }
        var handler = Handler()
        handler.postDelayed({
        }, 1000)

        lottie_view.imageAssetsFolder = "images"
        lottie_view.setAnimation("data.json")
        lottie_view.loop(false)
        lottie_view.setProgress(0f)
        lottie_view.addAnimatorListener(introListener)
        lottie_view.playAnimation()


    }
}