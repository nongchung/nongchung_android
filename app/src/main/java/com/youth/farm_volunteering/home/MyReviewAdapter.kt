package com.youth.farm_volunteering.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.MyPageMyReviewData
import com.youth.farm_volunteering.data.rvListInfoData
import com.youth.farm_volunteering.myactivity.MyReviewData


class MyReviewAdapter(var dataList: List<MyPageMyReviewData>) : RecyclerView.Adapter<MyReviewViewHolder>() {

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReviewViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_mypage_myreview, parent, false)
        return MyReviewViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: MyReviewViewHolder?, position: Int) {
        var builderDate = StringBuilder()
        var builderPeriod = StringBuilder()
        builderDate.append(dataList[position].startDate)
                .append(" 참여")
        builderPeriod.append(dataList[position].nhName)
                .append(" ")
                .append(dataList[position].period)

        Glide.with(holder!!.itemView.context)
                .load(dataList[position].farmImg) //String 줘서 이렇게?? //ㅇㅇ 그렇게
                .into(holder.myReviewFarmImg)
        holder.myReviewFarmName.text = dataList[position].name
        holder.myReviewNhName.text = builderPeriod.toString()
        holder.myReviewRatingBar.rating = dataList[position].star!! / 2.0f
        holder.myReviewComment.text = dataList[position].content
        holder.myReviewDate.text = builderDate.toString()

        if(!(dataList[position].img!![0].isEmpty()))
        {
            holder.myReviewImgRecyclerView.adapter = ReviewImageAdapter(dataList[position].img!!)
            holder.myReviewImgRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }

    }

}

