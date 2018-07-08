package com.youth.farm_volunteering.SignUp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_signup2.*

const val RESULT_SUCCESS: Int = 1
const val RESULT_CANCEL: Int = 2;

class SignupActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup2)
        setResult(RESULT_CANCEL)
        button_signup_submit.setOnClickListener {
            setResult(RESULT_SUCCESS)
//            intent.putExtra()
            finish()
        }

    }
}
