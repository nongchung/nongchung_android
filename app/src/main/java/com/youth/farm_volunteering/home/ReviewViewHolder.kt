package com.youth.farm_volunteering.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class ReviewViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var Reviewuimg: ImageView = itemView!!.findViewById(R.id.item_review_img)

    var Reviewname: TextView = itemView!!.findViewById(R.id.item_review_id)

    var Reviewdate: TextView = itemView!!.findViewById(R.id.item_review_date)

    var Reviewstar: TextView = itemView!!.findViewById(R.id.item_review_short_comment)

    var Reviewcontent: TextView = itemView!!.findViewById(R.id.item_review_comment)
    var reviewImageRecyclerView: RecyclerView = itemView!!.findViewById(R.id.review_img_rv)


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