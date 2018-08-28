package com.youth.farm_volunteering.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.rvListInfoData


class ReviewAdapter(var dataList: List<rvListInfoData>) : RecyclerView.Adapter<ReviewViewHolder>() {

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder?, position: Int) {
        Glide.with(holder!!.itemView.context)
                .load(dataList[position].uimg) //String 줘서 이렇게?? //ㅇㅇ 그렇게
                .into(holder.Reviewuimg)
        holder.Reviewname.text = dataList[position].name
        holder.Reviewdate.text = dataList[position].startDate
        holder.reviewRatingBar.rating = dataList[position].star!! / 2.0f
        holder.Reviewstar.text = String.format("%.1f", dataList[position].star!! / 2.0f)
        holder.Reviewcontent.text = dataList[position].content
        if(!(dataList[position].rvImages!![0].isEmpty()))
        {
            holder.reviewImageRecyclerView.adapter = ReviewImageAdapter(dataList[position].rvImages!!)
            holder.reviewImageRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }

    }

}

