package com.youth.farm_volunteering.Home.ThemaNonghwal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_thema.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThemaActivity : AppCompatActivity() {
    var themaList : List<ThemaListData>? = null
    lateinit var themaAdapter: ThemaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thema)
        var profileCall = ApplicationController.instance!!.networkService!!.thema(idx = 1)
        profileCall.enqueue(object : Callback<ThemaData> {
            override fun onFailure(call: Call<ThemaData>, t: Throwable?) {
                Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ThemaData>, response: Response<ThemaData>) {
                themaList = response.body().data


                themaAdapter = ThemaAdapter(themaList!!)


                thema_rv.layoutManager = LinearLayoutManager(this@ThemaActivity)
                thema_rv.adapter = themaAdapter

            }
        })

    }
}
