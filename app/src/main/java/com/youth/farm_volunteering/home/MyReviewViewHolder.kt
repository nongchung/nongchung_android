package com.youth.farm_volunteering.home

import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.item_mypage_myreview.view.*

class MyReviewViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var myReviewNhName : TextView = itemView!!.findViewById(R.id.textview_nh_name)
    var myReviewFarmName : TextView = itemView!!.findViewById(R.id.item_myreview_farm_name)
    var myReviewFarmImg : ImageView = itemView!!.findViewById(R.id.item_myreview_farm_img)
    var myReviewRatingBar : RatingBar = itemView!!.findViewById(R.id.item_myreview_rating_bar)
    var myReviewDate : TextView = itemView!!.findViewById(R.id.item_myreview_date)
    var myReviewComment : TextView = itemView!!.findViewById(R.id.item_myreview_comment)
    var myReviewImgRecyclerView : RecyclerView = itemView!!.findViewById(R.id.myreview_img_rv)
}