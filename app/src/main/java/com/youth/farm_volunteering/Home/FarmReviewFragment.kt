package com.youth.farm_volunteering.Home

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import java.util.ArrayList
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.FarmAdapter
import com.youth.farm_volunteering.Home.ReviewAdapter.Companion.people
import com.youth.farm_volunteering.Home.ReviewAdapter.Companion.peoples
import com.youth.farm_volunteering.R.id.review_rating_bar
import com.youth.farm_volunteering.data.DetailNonghwalResponseData
import com.youth.farm_volunteering.data.NonghwalData
import com.youth.farm_volunteering.data.ReviewResponseData
import com.youth.farm_volunteering.data.rvListInfoData
import kotlinx.android.synthetic.main.fragment_farm_introduce.*
import kotlinx.android.synthetic.main.fragment_farm_introduce.view.*
import kotlinx.android.synthetic.main.fragment_farm_review.*
import kotlinx.android.synthetic.main.fragment_farm_review.view.*
import kotlinx.android.synthetic.main.item_review.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Long.sum


class FarmReviewFragment : Fragment(){

    lateinit var reviewitems: ArrayList<ReviewData>
    lateinit var reviewAdapter : ReviewAdapter
    lateinit var reviewimageAdapter : ReviewImageAdapter

    lateinit var reviewimgitems: ArrayList<ReviewImageData>
    var ReviewList: List<rvListInfoData>? = null
    var ReviewImageList : List<rvListInfoData>? = null

    private  var linearLayoutManager : LinearLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_farm_review, container, false)
        //activity!!.supportFragmentManager.beginTransaction().add()
        val a = inflater.inflate(R.layout.item_review,container,false)

        var reviewCall  = ApplicationController.instance!!.networkService!!.review(4)

        reviewCall.enqueue(object : Callback<ReviewResponseData> {
            override fun onFailure(call: Call<ReviewResponseData>, t: Throwable?) {
                Toast.makeText(activity, "home request fail", Toast.LENGTH_SHORT).show()
                //Log.e("abc",t.toString())
            }
            override fun onResponse(call: Call<ReviewResponseData>, response: Response<ReviewResponseData>) {

                review_rating_bar.rating = peoples.toFloat()
                ReviewList = response.body().rvListInfo



                ReviewImageList = response.body().rvListInfo

                reviewAdapter = ReviewAdapter(ReviewList!!)




               // reviewimageAdapter = ReviewImageAdapter(ReviewImageList!!)
                v.review_rv.layoutManager = LinearLayoutManager(context)
                v.review_rv.adapter = reviewAdapter


               // v.review_img_rv.layoutManager = LinearLayoutManager(context)
               // v.review_img_rv.adapter = reviewimageAdapter



            }
        })


//        reviewitems = ArrayList()
//        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
//        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
//        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
//        reviewitems.add(ReviewData(R.drawable.image_1,R.drawable.image_1,"4.3점","승기","감사합니다."))
//
//        reviewAdapter = ReviewAdapter(reviewitems)
//        reviewAdapter.setOnItemClickListener(this.activity)

//        v.review_rv.layoutManager = LinearLayoutManager(this.activity!!.applicationContext)
//        v.review_rv.adapter = reviewAdapter



//RecyclerVIew안에 RecyclerView를 호출하기 위한 코드 그림 가로로 넣기 위해서...
//        reviewimgitems = ArrayList()
//
//        reviewimgitems.add(ReviewImageData(R.drawable.image_1))
//        reviewimgitems.add(ReviewImageData(R.drawable.image_1))
//        reviewimgitems.add(ReviewImageData(R.drawable.image_1))
//        reviewimgitems.add(ReviewImageData(R.drawable.image_1))
//
//        linearLayoutManager = LinearLayoutManager(activity , LinearLayoutManager.HORIZONTAL,false) // 괄호안에 activity도 변경 필요
//
//        reviewimgAdapter = ReviewImageAdapter(reviewimgitems)
        //a.reviewimageView.layoutManager = linearLayoutManager // 이걸바꾸자....
        //a.reviewimageView.adapter = reviewimgAdapter

        return v








    }


}