package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R

class ReviewAdapter(private var reviewitems : ArrayList<ReviewData>) : RecyclerView.Adapter<ReviewViewHolder>() {
//    private lateinit var onItemClick: View.OnClickListener

    override fun getItemCount(): Int = reviewitems.size
//    fun setOnItemClickListener(l : View.OnClickListener){
//
//        onItemClick = l;
//
//    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReviewViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_review,parent, false)

//        mainView.setOnClickListener(onItemClick)
        return ReviewViewHolder(mainView)
    }
    override fun onBindViewHolder(holder: ReviewViewHolder?, position: Int) {

        holder!!.FarmReviewImg.setImageResource(reviewitems[position].reviewImg)
        //holder!!.FarmReviewProfile.setImageResource(reviewitems[position].reviewProfile)
        //holder!!.FarmReviewID.text = reviewitems[position].reviewId
        //holder!!.FarmReviewPW.text = reviewitems[position].reviewPw
        holder!!.FarmReviewComment.text = reviewitems[position].reviewComment

    }

}