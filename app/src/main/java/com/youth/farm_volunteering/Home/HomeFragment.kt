package com.youth.farm_volunteering

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.asksira.loopingviewpager.LoopingViewPager
import com.asksira.loopingviewpagerdemo.DemoInfiniteAdapter
import com.youth.farm_volunteering.Home.FarmRecyAdapter
import com.youth.farm_volunteering.Home.FarmRecyData
import kotlinx.android.synthetic.main.activity_recycle_list.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : AppCompatActivity(), View.OnClickListener {

    lateinit var recycleItems: ArrayList<FarmRecyData>
    lateinit var recycleAdapter: FarmRecyAdapter

    //가로 조정
    private var linearLayoutManager : LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_list)
        recycleItems = ArrayList()
        recycleItems.add(FarmRecyData(R.drawable.image_1, "1박2일", "농활", "서울", "20000"))

        recycleAdapter = FarmRecyAdapter(recycleItems)
        recycleAdapter.setOnItemClickListener(this)

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = recycleAdapter

        recycleView!!.setLayoutManager(linearLayoutManager)

    }
    override fun onClick(v: View?) {
        //val idx : Int = recycleView.getChildAdapterPosition(v)
        //val name : String = recycleItems[idx].farmtitle
        //val profile : Int = recycleItems[idx].farmprofile
        val intent : Intent = Intent(applicationContext, FarmDetailActivity::class.java)
        startActivity(intent)
    }
}

/*

*/
