package com.youth.farm_volunteering.Review

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.youth.farm_volunteering.Bookmark.LikeData
import com.youth.farm_volunteering.Bookmark.LikeViewHolder
import com.youth.farm_volunteering.R

class ReviewAdapter (var ReviewItems : ArrayList<ReviewData>) : RecyclerView.Adapter<ReviewViewHolder>() {
    override fun getItemCount(): Int = ReviewItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReviewViewHolder {
        val review : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_review_pic, parent, false)
        return ReviewViewHolder(review)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder?, position: Int) {
        holder!!.picture.setImageURI(ReviewItems[position].pic)
    }

}