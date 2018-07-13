package com.youth.farm_volunteering.MyActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.fragment_myactivity.view.*
import kotlinx.android.synthetic.main.fragment_myactivity.view.*
import java.util.*


class MyActivityFragment : android.support.v4.app.Fragment() {

    lateinit var myList: ArrayList<MyData>
    lateinit var myAdapter : MyAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_myactivity, container, false)

        myList = ArrayList()
        myList.add(MyData(R.drawable.mymy_ing1,"제주 행복 감귤 농장 1박 2일","제주 서귀포시","20,000","2018.6.29","2018.6.30","3","17/20"))
        myList.add(MyData(R.drawable.mymy_ing1,"제주 행복 감귤 농장 1박 2일","제주 서귀포시","20,000","2018.6.29","2018.6.30","3","17/20"))
        myList.add(MyData(R.drawable.mymy_ing1,"제주 행복 감귤 농장 1박 2일","제주 서귀포시","20,000","2018.6.29","2018.6.30","3","17/20"))

        myAdapter = MyAdapter(myList)
        v.my_rv.layoutManager = LinearLayoutManager(this.activity)
        v.my_rv.adapter = myAdapter

        return v
    }
}