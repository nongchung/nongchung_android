package com.youth.farm_volunteering

import android.animation.Animator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.youth.farm_volunteering.Main.*
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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