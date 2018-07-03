package com.youth.farm_volunteering.Home

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import java.util.ArrayList
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_farm_detail.*
import kotlinx.android.synthetic.main.activity_farm_detail_review.*
import kotlinx.android.synthetic.main.activity_farm_detail_review.view.*


class FarmDetailReview : Fragment(){

    lateinit var reviewitems: ArrayList<ReviewData>
    lateinit var reviewAdapter : ReviewAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_farm_detail_review, container, false)
        //activity!!.supportFragmentManager.beginTransaction().add()

        reviewitems = ArrayList()
        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))

        reviewAdapter = ReviewAdapter(reviewitems)
//        reviewAdapter.setOnItemClickListener(this.activity)

        v.reviewView.layoutManager = LinearLayoutManager(this.activity.applicationContext)
        v.reviewView.adapter = reviewAdapter


        return v

    }


}