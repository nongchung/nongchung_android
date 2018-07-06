package com.youth.farm_volunteering.SignUp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.youth.farm_volunteering.Home.QandA.qandaFragment
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_signup1.*

class SignupActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup1)

        nextbutton.setOnClickListener {
            var signup2 = Intent(this, qandaFragment::class.java)
            startActivity(signup2)
        }
    }
}
