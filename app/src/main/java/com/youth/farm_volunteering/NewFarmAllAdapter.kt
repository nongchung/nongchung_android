package com.youth.farm_volunteering

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.Home.NewFarmAllViewHolder
import com.youth.farm_volunteering.NewFarmAllAdapter
import com.youth.farm_volunteering.Home.NewFarmItemViewHolder
import com.youth.farm_volunteering.data.AllNewData

class NewFarmAllAdapter(var dataList: List<AllNewData>) : RecyclerView.Adapter<NewFarmAllViewHolder>(){
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewFarmAllViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmnew_all, parent, false)
        return NewFarmAllViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: NewFarmAllViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(dataList[position].img)
                .into(holder.imageviewNewFarmall)
        holder.textviewNewFarmAddrall.setText(dataList[position].addr)
        holder.textviewNewFarmDateall.setText(dataList[position].period)
        holder.textviewNewFarmPriceall.text = dataList[position].price.toString()
        holder.textviewNewFarmTitleall.setText(dataList[position].name)
        if(dataList[position].isBooked!=null){
            when(dataList[position].isBooked){
                0-> holder.imageviewNewFarmBookmarkall.isSelected = false
                1-> holder.imageviewNewFarmBookmarkall.isSelected = true
            }
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("farm_img", dataList[position].img)
            intent.putExtra("farm_location", dataList[position].addr)
            intent.putExtra("farm_price", dataList[position].price)
            intent.putExtra("farm_days", dataList[position].period)
            intent.putExtra("farm_name", dataList[position].name)

            //추천수
            //설명
            holder.itemView.context.startActivity(intent)
        }
    }
}