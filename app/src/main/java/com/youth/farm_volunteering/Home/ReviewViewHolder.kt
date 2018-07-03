package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class ReviewViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var FarmReviewImg : ImageView = itemView!!.findViewById(R.id.review_img)
    var FarmReviewProfile : ImageView = itemView!!.findViewById(R.id.review_profile_img)
    var FarmReviewID : TextView = itemView!!.findViewById(R.id.review_id)
    var FarmReviewPW : TextView = itemView!!.findViewById(R.id.review_pw)
    var FarmReviewComment : TextView = itemView!!.findViewById(R.id.review_comment)
//
//    holder!!.FarmReviewImg.image = reviewitems[position].reviewImg
//    holder!!.FarmReviewProfile.image = reviewitems[position].reviewProfile
//    holder!!.FarmReviewID.text = reviewitems[position].reviewId
//    holder!!.FarmReviewPW.text = reviewitems[position].reviewId
//    holder!!.FarmReviewComment.text = reviewitems[position].reviewId
//
//    var FarmSchedule1 : TextView = itemView!!.findViewById(R.id.schedule_1)
//
//    var FarmSchedule2 : TextView = itemView!!.findViewById(R.id.schedule_2)
//
//    var FarmSchedule3 : TextView = itemView!!.findViewById(R.id.schedule_3)

}