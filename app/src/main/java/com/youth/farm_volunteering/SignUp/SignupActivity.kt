package com.youth.farm_volunteering.SignUp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.youth.farm_volunteering.R

class SignupActivity : AppCompatActivity() {

    var toolbar : android.support.v7.widget.Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        toolbar = findViewById(R.id.toolbar_signup)
        setSupportActionBar(toolbar)
    }
}
