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

        var sharedPreference = getSharedPreferences(LoginToken.PREF_KEY, Context.MODE_PRIVATE);
        var editor = sharedPreference.edit();

        LoginToken.token = sharedPreference.getString(LoginToken.PREF_KEY, null);

        LoginData.name = sharedPreference.getString("uName","")
        LoginData.point = sharedPreference.getInt("uPoint",0)
        LoginData.img = sharedPreference.getString("uImg","")
        LoginData.nickname = sharedPreference.getString("uNickname","")
        LoginData.age = sharedPreference.getInt("uAge",0)

        editor.commit()

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