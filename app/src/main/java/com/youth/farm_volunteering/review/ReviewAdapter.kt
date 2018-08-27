package com.youth.farm_volunteering.review

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R

class ReviewAdapter (var ReviewItems : ArrayList<Uri>) : RecyclerView.Adapter<ReviewViewHolder>() {
    override fun getItemCount(): Int = ReviewItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReviewViewHolder {
        val review : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_review_pic, parent, false)
        return ReviewViewHolder(review)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder?, position: Int) {

        Glide.with(holder!!.itemView.context)
                .load(ReviewItems[position])
                .into(holder!!.picture)
    }

}