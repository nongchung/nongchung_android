package com.youth.farm_volunteering.SignUp

import android.app.Activity
import android.app.DatePickerDialog
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
import com.youth.farm_volunteering.R.id.*
import com.youth.farm_volunteering.StartActivity
import com.youth.farm_volunteering.data.DupResponseData
import kotlinx.android.synthetic.main.activity_signup2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val RESULT_SUCCESS: Int = 1
const val RESULT_CANCEL: Int = 2;
const val BUNDLE_KEY_SEX: String = "BUNDLE_KEY_SEX";
const val BUNDLE_KEY_BIRTH: String = "BUNDLE_KEY_BIRTH";
const val BUNDLE_KEY_NICKNAME: String = "BUNDLE_KEY_NICKNAME";
const val BUNDLE_KEY_NAME: String = "BUNDLE_KEY_NAME";
const val BUNDLE_KEY_PHONENUMBER: String = "BUNDLE_KEY_PHONENUMBER";

class SignupActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup2)

        textView22.visibility = INVISIBLE
        imageView.visibility = INVISIBLE

        edittext_signup_nickname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val nick = p0.toString()
                if(nick == ""){}
                else{
                var nick_dup = ApplicationController.instance!!.networkService!!.nickDup(nick)
                nick_dup.enqueue(object : Callback<DupResponseData> {
                    override fun onFailure(call: Call<DupResponseData>?, t: Throwable?) {
                        Toast.makeText(this@SignupActivity2, "nick_dup request fail", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<DupResponseData>?, response: Response<DupResponseData>?) {
                        if (response!!.body().message == "available") {
                            textView22.visibility = INVISIBLE
                            imageView.visibility = INVISIBLE
                        } else if (response!!.body().message == "duplication") {
                            textView22.visibility = VISIBLE
                            imageView.visibility = VISIBLE
                        }
                    }
                })}
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })


        setResult(RESULT_CANCEL)
        textview_signup_birth.setOnClickListener {
            intent.putExtra(BUNDLE_KEY_NAME, edittext_signup_name.text.toString())
            intent.putExtra(BUNDLE_KEY_BIRTH, textview_signup_birth.text.toString())
            intent.putExtra(BUNDLE_KEY_NICKNAME, edittext_signup_nickname.text.toString())
            intent.putExtra(BUNDLE_KEY_PHONENUMBER, edittext_signup_phonenumber.text.toString())
            intent.putExtra(BUNDLE_KEY_SEX, edittext_signup_gender.text.toString())
        }


        button_signup_submit.setOnClickListener {
            if (edittext_signup_name.text.toString() == "") {
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (edittext_signup_nickname.text.toString() == "") {
                Toast.makeText(this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (textview_signup_birth.text.toString() == "") {
                Toast.makeText(this, "생일을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (edittext_signup_gender.text.toString() == "") {
                Toast.makeText(this, "성별을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (edittext_signup_phonenumber.text.toString() == "") {
                Toast.makeText(this, "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (textView22.visibility == VISIBLE) {
                Toast.makeText(this, "닉네임을 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                var signup = Intent(this, StartActivity::class.java)
                startActivityForResult(signup, 0)

                intent.putExtra(BUNDLE_KEY_NAME, edittext_signup_name.text.toString())
                intent.putExtra(BUNDLE_KEY_BIRTH, textview_signup_birth.text.toString())
                intent.putExtra(BUNDLE_KEY_NICKNAME, edittext_signup_nickname.text.toString())
                intent.putExtra(BUNDLE_KEY_PHONENUMBER, edittext_signup_phonenumber.text.toString())
                intent.putExtra(BUNDLE_KEY_SEX, edittext_signup_gender.text.toString())
                setResult(RESULT_SUCCESS, intent)
// 임시보류

//            intent.putExtra()
                finish()


            }
        }

    }
}
