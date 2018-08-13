//package com.youth.farm_volunteering.home.ThemaNonghwal
//
//import android.os.Bundle
//import android.os.Handler
//import android.support.v4.app.Fragment
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.ProgressBar
//import android.widget.Toast
//import com.asksira.loopingviewpagerdemo.ApplicationController
//import com.youth.farm_volunteering.R
//import kotlinx.android.synthetic.main.activity_thema.*
//import kotlinx.android.synthetic.main.activity_thema.view.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class ThemaFragment : Fragment() {
//
//    var themaList : List<ThemaListData>? = null
//    var handler : Handler? = null
//    var themeRecyclerview : RecyclerView? = null
//    var themeImage : ImageView? = null
//    var progresbar : ProgressBar? = null
//
//    lateinit var themaAdapter: ThemaAdapter
//    var getPosition : Int? = null
//    var getImage : Int? = null
//
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val v = inflater!!.inflate(R.layout.activity_thema, container, false)
//
//        themeRecyclerview = v.findViewById(R.id.thema_rv)
//        themeImage = v.findViewById(R.id.imageviewThemeImage)
//        progresbar = v.findViewById(R.id.progressbar_read_themefragment)
//
//        var getPosition : Int = arguments.getInt("themePosition", 0)
//        var getImage : Int = arguments.getInt("themeImage", 0)
//
//        handler = Handler()
//
//        themeRecyclerview!!.visibility = View.GONE
//        themeImage!!.visibility = View.GONE
//        progresbar!!.visibility = View.VISIBLE
//
//        handler!!.postDelayed({
//            getThemeList(getPosition+1 , getImage)
//
//        },2000)
//
//
//        return v
//    }
//
//    fun getThemeList(getPosition : Int, getImage : Int){
//        var profileCall = ApplicationController.instance!!.networkService!!.thema(getPosition)
//        profileCall.enqueue(object : Callback<ThemaData> {
//            override fun onFailure(call: Call<ThemaData>, t: Throwable?) {
//                Toast.makeText(activity.applicationContext, "home request fail", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(call: Call<ThemaData>, response: Response<ThemaData>) {
//
//                progresbar!!.visibility = View.GONE
//                themeImage!!.visibility = View.VISIBLE
//                themeRecyclerview!!.visibility = View.VISIBLE
//
//                themeImage!!.setImageResource(getImage)
//                themeImage!!.adjustViewBounds = true
//                themeImage!!.scaleType = ImageView.ScaleType.FIT_START
//
//                themaList = response.body().data
//
//                themaAdapter = ThemaAdapter(themaList!!)
//
//                themeRecyclerview!!.layoutManager = LinearLayoutManager(activity.applicationContext)
//                themeRecyclerview!!.adapter = themaAdapter
//            }
//        })
//    }
//}