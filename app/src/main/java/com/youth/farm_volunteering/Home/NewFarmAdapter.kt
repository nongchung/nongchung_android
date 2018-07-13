package com.youth.farm_volunteering.Home

import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.FarmDetailActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.HomeNonghwalData

class NewFarmAdapter(var dataList: ArrayList<HomeNonghwalData>) : RecyclerView.Adapter<NewFarmItemViewHolder>(){
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewFarmItemViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmnew, parent, false)
        return NewFarmItemViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: NewFarmItemViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(dataList[position].img)
                .into(holder.imageviewNewFarm)
        holder.textviewNewFarmAddr.setText(dataList[position].addr)
        holder.textviewNewFarmDate.setText(dataList[position].period)
        holder.textviewNewFarmPrice.text = dataList[position].price.toString()
        holder.textviewNewFarmTitle.setText(dataList[position].name)

        holder.imageviewNewFarmBookmark.setOnClickListener {
            Toast.makeText(holder.itemView.context,dataList[position].isBooked.toString(),Toast.LENGTH_SHORT).show()

            if(dataList[position].isBooked!=0){
                holder.imageviewNewFarmBookmark.isSelected = false
                dataList[position].isBooked=0
            }
            else
            {
                holder.imageviewNewFarmBookmark.isSelected = true
                dataList[position].isBooked=1
            }
        }


        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("populData", dataList[position]  as Parcelable)

            //추천수
            //설명
            holder.itemView.context.startActivity(intent)
        }
    }
}