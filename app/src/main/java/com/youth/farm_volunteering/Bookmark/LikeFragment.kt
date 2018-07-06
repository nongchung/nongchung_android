package com.youth.farm_volunteering.Bookmark

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import java.util.ArrayList
import kotlinx.android.synthetic.main.fragment_like.*

class LikeFragment : Fragment() {

    lateinit var likeList: ArrayList<LikeData>
    lateinit var likeAdapter: LikeAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        likeList = ArrayList()

        likeList!!.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
        likeList!!.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
        likeList!!.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
        likeList!!.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
        likeList!!.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
        likeList!!.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))
        likeList!!.add(LikeData(R.drawable.image_1, "제주 행복 감귤 농장 1박 2일", "제주 서귀포시", "20,000원"))

        likeAdapter = LikeAdapter(likeList!!)


        val v = inflater!!.inflate(R.layout.fragment_like, container, false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        fragment_home_weeklyHotFarm_showAll_txt.setOnClickListener(this)
        fragment_like_rv.layoutManager = LinearLayoutManager(context)
        fragment_like_rv.adapter = likeAdapter

    }

}