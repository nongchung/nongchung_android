package com.youth.farm_volunteering.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

//        Glide.with(holder.itemView.context)
//                .load(dataList[position].farmImg)
//                .into(holder.imageviewPopulFarmFarm)
//        Glide.with(holder.itemView.context)
//                .load(dataList[position].farmerImg)
//                .into(holder.imageviewPopulFarmFarmer)

        holder.textviewPopulFarmTitle.text = dataList[position].name
        holder.textviewPopulFarmAddr.text = dataList[position].addr

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "구현 예정입니다!", Toast.LENGTH_SHORT).show()

//            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
//            intent.putExtra("populData", dataList[position] as Parcelable)
//
//            holder.itemView.context.startActivity(intent)
        }

    }

}