package com.youth.farm_volunteering

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_showall.*
import java.util.ArrayList

class ShowAllFragment : Fragment() {
    lateinit var farmAdapter: FarmAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_showall, container, false)

        var farmList : ArrayList<FarmData>? = null


        farmList = ArrayList()

        farmList!!.add(FarmData(R.drawable.image_1, R.drawable.todays_pick, "1서울 회기","("+"14000"+"원, ","1박"+")","딸기농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_2, R.drawable.todays_pick, "2서울 합정","("+"14000"+"원, ","1박"+")","사과농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_3, R.drawable.todays_pick, "3서울 홍대","("+"14000"+"원, ","1박"+")","배농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_4, R.drawable.todays_pick, "4서울 신촌","("+"14000"+"원, ","1박"+")","감귤농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_5, R.drawable.todays_pick, "5서울 노원","("+"14000"+"원, ","1박"+")","메론농장"
                , 142.toString()))
        farmList!!.add(FarmData(R.drawable.image_6, R.drawable.todays_pick, "6서울 강남","("+"14000"+"원, ","1박"+")","레몬농장"
                , 142.toString()))

        farmAdapter = FarmAdapter(farmList!!)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_showall_rv.layoutManager = LinearLayoutManager(context)

        fragment_showall_rv.adapter = farmAdapter

    }
}
