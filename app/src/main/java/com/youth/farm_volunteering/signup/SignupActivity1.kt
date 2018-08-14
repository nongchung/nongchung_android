package com.youth.farm_volunteering.signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.StartActivity
import com.youth.farm_volunteering.data.DefaultResponseData
import com.youth.farm_volunteering.data.DupResponseData
import kotlinx.android.synthetic.main.activity_signup1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val RESULT_REQUEST_LOGIN: Int = 3;
const val BUNDLE_KEY_EMAIL: String = "BUNDLE_KEY_EMAIL"
const val BUNDLE_KEY_PASSWORD: String = "BUNDLE_KEY_PASSWORD"

class SignupActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)

        textView5.visibility = INVISIBLE
        imageView5.visibility = INVISIBLE

        textView2.visibility = INVISIBLE
        imageView2.visibility = INVISIBLE

        edittext_signup_email.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val email = p0.toString()
                if(email == ""){}
                else{
                var email_dup = ApplicationController.instance!!.networkService!!.emailDup(email)
                email_dup.enqueue(object : Callback<DupResponseData>{
                    override fun onFailure(call: Call<DupResponseData>?, t: Throwable?) {
                        Toast.makeText(this@SignupActivity1, "email_dup request fail", Toast.LENGTH_SHORT).show()
                    }
                    override fun onResponse(call: Call<DupResponseData>?, response: Response<DupResponseData>?) {
                        if(response!!.body().message == "available"){
                            textView5.visibility = INVISIBLE
                            imageView5.visibility = INVISIBLE
                        }
                        else if(response!!.body().message == "duplication"){
                            textView5.visibility = VISIBLE
                            imageView5.visibility = VISIBLE
                        }
                    }
                } )}
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        edittext_signup_password2.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(edittext_signup_password1.text.toString() != p0.toString()){
                    textView2.visibility = VISIBLE
                    imageView2.visibility = VISIBLE
                }
                else if(edittext_signup_password1.text.toString() == p0.toString()){
                    textView2.visibility = INVISIBLE
                    imageView2.visibility = INVISIBLE
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        nextbutton.setOnClickListener {

            if(textView5.visibility == VISIBLE || textView2.visibility == VISIBLE){
                Toast.makeText(this, "이메일 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(textView5.text.toString() == "" || textView2.text.toString() == ""){
                Toast.makeText(this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(edittext_signup_password1.text.toString() == ""){
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else if(edittext_signup_password1.text.toString() == ""){
                Toast.makeText(this, "비밀번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                var signup2 = Intent(this, SignupActivity2::class.java)
                startActivityForResult(signup2, 0)
                //다이얼로그 띄우기
//            val fm = supportFragmentManager
//            val dialogFragment = ApplicationCancelDialog(this)
//            dialogFragment.show()
            }
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
                Toast.makeText(this@SignupActivity1, name + "님, 농활청춘에 오신 것을 환영합니다!", Toast.LENGTH_SHORT).show()
                intent.putExtra(BUNDLE_KEY_EMAIL, email)
                intent.putExtra(BUNDLE_KEY_PASSWORD, password)
                setResult(RESULT_REQUEST_LOGIN, intent)
                finish()

            }
        })
    }
}
