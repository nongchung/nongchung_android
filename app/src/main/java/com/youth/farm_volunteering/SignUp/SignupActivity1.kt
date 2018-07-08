package com.youth.farm_volunteering.SignUp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.Home.QandA.qandaFragment
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.DefaultResponseData
import com.youth.farm_volunteering.data.MyPageResponseData
import kotlinx.android.synthetic.main.activity_signup1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val RESULT_REQUEST_LOGIN: Int = 3;

class SignupActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)

        nextbutton.setOnClickListener {
            var signup2 = Intent(this, SignupActivity2::class.java)
            startActivityForResult(signup2, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            RESULT_CANCEL ->
                finish()
            else ->
                requestRegistration(edittext_signup_email.text.toString(), edittext_signup_password1.text.toString(), data!!.getStringExtra(BUNDLE_KEY_NICKNAME), data!!.getStringExtra(BUNDLE_KEY_NAME), data!!.getIntExtra(BUNDLE_KEY_SEX, 0), data!!.getStringExtra(BUNDLE_KEY_PHONENUMBER), data!!.getStringExtra(BUNDLE_KEY_BIRTH))
        }
    }

    private fun requestRegistration(email: String, password: String, nickname: String, name: String, sex: Int, handphone: String, birth: String) {

        var mypageCall = ApplicationController.instance!!.networkService!!.registration(email, password, nickname, name, sex, handphone, birth)
        mypageCall.enqueue(object : Callback<DefaultResponseData> {
            override fun onFailure(call: Call<DefaultResponseData>, t: Throwable?) {
                Toast.makeText(this@SignupActivity1, "signup request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DefaultResponseData>, response: Response<DefaultResponseData>) {
                setResult(RESULT_REQUEST_LOGIN)
                finish()

            }
        })
    }
}
