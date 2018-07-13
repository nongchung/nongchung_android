package com.youth.farm_volunteering.Home.ThemaNonghwal

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.Home.FarmProfile.FarmProfileViewHolder
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.FarmerProfileData

class ThemaAdapter(var themaList: List<ThemaListData>) : RecyclerView.Adapter<ThemaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ThemaViewHolder {
        val mainView: View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_thema, parent, false)
        return ThemaViewHolder(mainView)
    }

    override fun getItemCount(): Int = themaList.size

    override fun onBindViewHolder(holder: ThemaViewHolder?, position: Int) {
        Glide.with(holder!!.itemView.context)
                .load(themaList[position].flmg)
                .into(holder.themaImage)
        holder.themanhName.text = themaList[position].name
        holder.themaPeriod.text = themaList[position].period
        holder.themaAddress.text = themaList[position].addr
        holder.themaPrice.text = themaList[position].price.toString()
        holder.themaDate.text = themaList[position].period

    }
}