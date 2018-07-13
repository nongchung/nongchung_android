package com.youth.farm_volunteering.MyActivity

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.FarmDetailActivity
import com.youth.farm_volunteering.Home.MyactivityViewHolder
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.WeekFarmItemViewHolder
import com.youth.farm_volunteering.data.MyActivityData
import com.youth.farm_volunteering.data.WeekNonghwalData


class MyactivityAdapter(var mydataList: List<MyActivityData>) : RecyclerView.Adapter<MyactivityViewHolder>(){
    override fun getItemCount(): Int = mydataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyactivityViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_myactivity, parent, false)
        return MyactivityViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: MyactivityViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
                .load(mydataList[position].img)
                .into(holder.img)
        holder.startDate.text = mydataList[position].startDate
        holder.endDate.text = mydataList[position].endDate
        holder.addr.text = mydataList[position].addr
       // holder.period.text = mydataList[position].period
        holder.name.text = mydataList[position].name
        holder.price.text = mydataList[position].price.toString()
//        if(mydataList[position].isBooked!=null){
//            when(mydataList[position].isBooked){
//                0-> holder.isBooked.isSelected = false
//                1-> holder.isBooked.isSelected = true
//            }
//        }
//        holder.starNum.text = mydataList[position].star.toString()



        }
    }