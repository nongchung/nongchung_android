package com.youth.farm_volunteering.home.ThemaNonghwal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_thema.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThemaActivity : AppCompatActivity() {
//    var themaList : List<ThemaListData>? = null
//    lateinit var themaAdapter: ThemaAdapter
//    var getPosition : Int? = null
//    var getImage : Int? = null

    var themaList : List<ThemaListData>? = null
    var handler : Handler? = null
    var themeRecyclerview : RecyclerView? = null
    var themeImage : ImageView? = null
    var progresbar : ProgressBar? = null
//
    lateinit var themaAdapter: ThemaAdapter
    var getPosition : Int? = null
    var getImage : Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thema)

        themeRecyclerview = findViewById(R.id.thema_rv)
        themeImage = findViewById(R.id.imageviewThemeImage)
        progresbar = findViewById(R.id.progressbar_read_themefragment)

        getPosition = intent.getIntExtra("themePosition",0)
        getImage = intent.getIntExtra("themeImage", 0)

        handler = Handler()

        themeRecyclerview!!.visibility = View.GONE
        themeImage!!.visibility = View.GONE
        progresbar!!.visibility = View.VISIBLE
        
        getThemeList(getPosition!! +1 , getImage!!)
        
//        var getThemeList = ApplicationController.instance!!.networkService!!.thema(getPosition!!)
//        getThemeList.enqueue(object : Callback<ThemaData> {
//            override fun onFailure(call: Call<ThemaData>, t: Throwable?) {
//                Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(call: Call<ThemaData>, response: Response<ThemaData>) {
//                themaList = response.body().dataMy
//                imageviewThemeImage.setImageResource(getImage!!)
//                imageviewThemeImage.adjustViewBounds = true
//                imageviewThemeImage.scaleType = ImageView.ScaleType.FIT_START
//
//                themaAdapter = ThemaAdapter(themaList!!)
//
//                thema_rv.layoutManager = LinearLayoutManager(this@ThemaActivity)
//                thema_rv.adapter = themaAdapter
//
//            }
//        })

    }

    fun getThemeList(getPosition : Int, getImage : Int){
        var getThemeList = ApplicationController.instance!!.networkService!!.thema(getPosition)
        getThemeList.enqueue(object : Callback<ThemaData> {
            override fun onFailure(call: Call<ThemaData>, t: Throwable?) {
                Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ThemaData>, response: Response<ThemaData>) {

                progresbar!!.visibility = View.GONE
                themeImage!!.visibility = View.VISIBLE
                themeRecyclerview!!.visibility = View.VISIBLE

                themeImage!!.setImageResource(getImage)
                themeImage!!.adjustViewBounds = true
                themeImage!!.scaleType = ImageView.ScaleType.FIT_START

                themaList = response.body().data

                themaAdapter = ThemaAdapter(themaList!!)

                themeRecyclerview!!.layoutManager = LinearLayoutManager(applicationContext)
                themeRecyclerview!!.adapter = themaAdapter
            }
        })
    }
}
