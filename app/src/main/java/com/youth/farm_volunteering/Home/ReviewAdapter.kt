package com.youth.farm_volunteering.Home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.review_rating_bar
import com.youth.farm_volunteering.data.FriendInfoData
import com.youth.farm_volunteering.data.rvListInfoData
import kotlinx.android.synthetic.main.fragment_farm_review.*


class ReviewAdapter(var dataList: List<rvListInfoData>) : RecyclerView.Adapter<ReviewViewHolder>() {




    override fun getItemCount(): Int = dataList.size



    //private lateinit var onIntroClick: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_review, parent, false)
        //mainView.setOnClickListener(onIntroClick)
        return ReviewViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder?, position: Int) {
        Glide.with(holder!!.itemView.context)
                .load(dataList[position].uimg) //String 줘서 이렇게??
                .into(holder.Reviewuimg)
        holder.Reviewname.text = dataList[position].name
        holder.Reviewdate.text = dataList[position].startDate

        //ratingAdd += dataList[position].star!!.toInt()
        holder.Reviewstar.text = dataList[position].star.toString()
        holder.Reviewcontent.text = dataList[position].content
        holder.reviewImageRecyclerView.adapter = ReviewImageAdapter(dataList[position].rvImages!!)
        holder.reviewImageRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)



    }

}

