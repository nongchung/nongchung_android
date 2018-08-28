package com.youth.farm_volunteering.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.MyPageMyReviewData
import com.youth.farm_volunteering.data.MyPageMyReviewResponseData
import kotlinx.android.synthetic.main.activity_my_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyReviewActivity : AppCompatActivity() {

    var mypageMyReviewList : List<MyPageMyReviewData>? = null
    var mypageMyReviewAdapter : MyReviewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_review)

        val getMyReview = ApplicationController.instance!!.networkService!!.getMyReview()
        getMyReview.enqueue(object : Callback<MyPageMyReviewResponseData>{
            override fun onFailure(call: Call<MyPageMyReviewResponseData>?, t: Throwable?) {
                Toast.makeText(applicationContext, "get my review request fail", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MyPageMyReviewResponseData>?, response: Response<MyPageMyReviewResponseData>?) {
                if(response!!.code() == 200){
                    if(response!!.isSuccessful){
                        if(response.body().data!!.size != 0) {
                            recyclerview_myreview.visibility = View.VISIBLE
                            imageview_no_myreview.visibility = View.GONE

                            mypageMyReviewList = response.body().data
                            mypageMyReviewAdapter = MyReviewAdapter(mypageMyReviewList!!)
                            recyclerview_myreview.adapter = mypageMyReviewAdapter
                            recyclerview_myreview.layoutManager = LinearLayoutManager(applicationContext)
                        } else{
                            recyclerview_myreview.visibility = View.GONE
                            imageview_no_myreview.visibility = View.VISIBLE
                            imageview_no_myreview.setImageResource(R.drawable.no_mywrite2)
                            imageview_no_myreview.scaleType = ImageView.ScaleType.FIT_XY
                        }
                    }
                }
            }

        })
    }
}
