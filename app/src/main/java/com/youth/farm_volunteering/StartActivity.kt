package com.youth.farm_volunteering

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.Main.MainActivity
import com.youth.farm_volunteering.R.id.*
import com.youth.farm_volunteering.SignUp.*
import com.youth.farm_volunteering.data.DefaultResponseData
import com.youth.farm_volunteering.login.LoginActivity
import kotlinx.android.synthetic.main.activity_signup1.*
import kotlinx.android.synthetic.main.activity_signup2.*
import kotlinx.android.synthetic.main.activity_start.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val RESULT_REQUEST_LOGIN: Int = 3;

class StartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        start_btn.setOnClickListener {
//                var signup = Intent(this, StartActivity::class.java)
//                startActivityForResult(signup, 0)
// 임시보류

//            intent.putExtra()
            finish()
        }
    }

}
