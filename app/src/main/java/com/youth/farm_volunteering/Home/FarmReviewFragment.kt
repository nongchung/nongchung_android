package com.youth.farm_volunteering.Home

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import java.util.ArrayList
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_farm_review.view.*
import kotlinx.android.synthetic.main.item_review.view.*


class FarmReviewFragment : Fragment(){

    lateinit var reviewitems: ArrayList<ReviewData>
    lateinit var reviewAdapter : ReviewAdapter

    lateinit var reviewimgitems: ArrayList<ReviewImageData>
    lateinit var reviewimgAdapter : ReviewImageAdapter

    private  var linearLayoutManager : LinearLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_farm_review, container, false)
        //activity!!.supportFragmentManager.beginTransaction().add()
        val a = inflater.inflate(R.layout.item_review,container,false)

        reviewitems = ArrayList()
        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))

        reviewAdapter = ReviewAdapter(reviewitems)
//        reviewAdapter.setOnItemClickListener(this.activity)

        v.review_rv.layoutManager = LinearLayoutManager(this.activity!!.applicationContext)
        v.review_rv.adapter = reviewAdapter



//RecyclerVIew안에 RecyclerView를 호출하기 위한 코드 그림 가로로 넣기 위해서...
        reviewimgitems = ArrayList()

        reviewimgitems.add(ReviewImageData(R.drawable.image_1))
        reviewimgitems.add(ReviewImageData(R.drawable.image_1))
        reviewimgitems.add(ReviewImageData(R.drawable.image_1))
        reviewimgitems.add(ReviewImageData(R.drawable.image_1))

        linearLayoutManager = LinearLayoutManager(activity , LinearLayoutManager.HORIZONTAL,false) // 괄호안에 activity도 변경 필요

        reviewimgAdapter = ReviewImageAdapter(reviewimgitems)
        //a.reviewimageView.layoutManager = linearLayoutManager // 이걸바꾸자....
        //a.reviewimageView.adapter = reviewimgAdapter

        return v








    }


}