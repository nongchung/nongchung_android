package com.youth.farm_volunteering.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.ReviewResponseData
import com.youth.farm_volunteering.data.rvListInfoData
import kotlinx.android.synthetic.main.fragment_farm_review.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class FarmReviewFragment : Fragment(){

    lateinit var reviewitems: ArrayList<ReviewData>
    lateinit var reviewAdapter : ReviewAdapter
    lateinit var reviewimageAdapter : ReviewImageAdapter

    var totalScore : TextView? = null
    var totalRatingBar : RatingBar? = null

    lateinit var reviewimgitems: ArrayList<ReviewImageData>
    var ReviewList: List<rvListInfoData>? = null
    var ReviewImageList : List<String>? = null

    private  var linearLayoutManager : LinearLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_farm_review, container, false)

        totalScore = v.findViewById(R.id.review_score)
        totalRatingBar = v.findViewById(R.id.review_rating_bar)

        var nhIdx : Int = arguments.getInt("nhIdx")

        var reviewCall  = ApplicationController.instance!!.networkService!!.review(nhIdx)
        reviewCall.enqueue(object : Callback<ReviewResponseData> {
            override fun onFailure(call: Call<ReviewResponseData>, t: Throwable?) {
                Toast.makeText(activity.applicationContext, "review request fail", Toast.LENGTH_SHORT).show()
                //Log.e("abc",t.toString())
            }
            override fun onResponse(call: Call<ReviewResponseData>, response: Response<ReviewResponseData>) {

                if (response.code() == 200) {
                    if (response.isSuccessful) {
                        if (response.body().message == "Success to Get Review List") {
                            ReviewList = response.body().rvListInfo
                            reviewAdapter = ReviewAdapter(ReviewList!!)
                            v.review_rv.layoutManager = LinearLayoutManager(context)
                            v.review_rv.adapter = reviewAdapter

                            var totalScoreTemp = 0f

                            for (i in 0 until ReviewList!!.size) {
                                totalScoreTemp = totalScoreTemp.plus(ReviewList!![i].star!! / 2.0f)
                            }
                            totalScore!!.text = String.format("%.1f", totalScoreTemp!! / ReviewList!!.size.toFloat())
                            totalRatingBar!!.rating = totalScoreTemp / ReviewList!!.size.toFloat()
                        }
                    }
                }else if(response.code() == 400){
                    v.review_rv.visibility = View.GONE
                    v.constraintlayout_avg.visibility = View.GONE
                    v.imageview_noreviews.visibility = View.VISIBLE
                }
            }
        })
        return v

    }


}