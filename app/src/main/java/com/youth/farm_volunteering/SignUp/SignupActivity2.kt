package com.youth.farm_volunteering.SignUp

import android.app.Activity
import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_signup2.*

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
        setResult(RESULT_CANCEL)
        textview_signup_birth.setOnClickListener {
            intent.putExtra(BUNDLE_KEY_NAME, edittext_signup_name.text.toString())
            intent.putExtra(BUNDLE_KEY_BIRTH, textview_signup_birth.text.toString())
            intent.putExtra(BUNDLE_KEY_NICKNAME, edittext_signup_nickname.text.toString())
            intent.putExtra(BUNDLE_KEY_PHONENUMBER, edittext_signup_phonenumber.text.toString())
            intent.putExtra(BUNDLE_KEY_SEX, edittext_signup_gender.text.toString())
        }
        button_signup_submit.setOnClickListener {

            intent.putExtra(BUNDLE_KEY_NAME, edittext_signup_name.text.toString())
            intent.putExtra(BUNDLE_KEY_BIRTH, textview_signup_birth.text.toString())
            intent.putExtra(BUNDLE_KEY_NICKNAME, edittext_signup_nickname.text.toString())
            intent.putExtra(BUNDLE_KEY_PHONENUMBER, edittext_signup_phonenumber.text.toString())
            intent.putExtra(BUNDLE_KEY_SEX, edittext_signup_gender.text.toString())
            setResult(RESULT_SUCCESS, intent)
//            intent.putExtra()
            finish()
        }

    }
}
