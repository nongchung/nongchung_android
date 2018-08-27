package com.youth.farm_volunteering.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R

class ReviewImageAdapter(var dataList: List<String>?) : RecyclerView.Adapter<ReviewImageViewHolder>() {
    override fun getItemCount(): Int{
        when(dataList){
            null -> return 1
            else -> return dataList!!.size
        }
    }

    //private lateinit var onIntroClick: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewImageViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_review_img, parent, false)
        //mainView.setOnClickListener(onIntroClick)
        return ReviewImageViewHolder(mainView)
    }


    override fun onBindViewHolder(holder: ReviewImageViewHolder?, position: Int) {

        if(dataList!=null) {
            Glide.with(holder!!.itemView.context)
                    .load(dataList!![position]) //String 줘서 이렇게??
                    .into(holder.FarmBoxReviewImg)
        } else{                                     //기본이미지 주기
            Glide.with(holder!!.itemView.context)
                    .load(R.drawable.review_default)
                    .into(holder.FarmBoxReviewImg)
        }
    }
}