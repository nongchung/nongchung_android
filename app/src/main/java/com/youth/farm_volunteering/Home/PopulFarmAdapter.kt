package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.PopulFarmData

class PopulFarmAdapter(var dataList: List<PopulFarmData>) : RecyclerView.Adapter<PopulFarmItemViewHolder>(){
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulFarmItemViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmpopul, parent, false)
        return PopulFarmItemViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: PopulFarmItemViewHolder, position: Int) {

//        holder.imageviewPopulFarmFarm
//        holder.imageviewPopulFarmFarmer
        holder.textviewPopulFarmTitle.text = dataList[position].name
        holder.textviewPopulFarmAddr.text = dataList[position].addr
    }

}