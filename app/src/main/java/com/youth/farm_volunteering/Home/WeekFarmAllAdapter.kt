package com.youth.farm_volunteering.Home

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.FarmDetailActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.WeekFarmItemViewHolder
import com.youth.farm_volunteering.data.WeekNonghwalAllData
import com.youth.farm_volunteering.data.WeekNonghwalData

class WeekFarmAllAdapter(var dataListWeek: List<WeekNonghwalData>) : RecyclerView.Adapter<WeekFarmAllViewHolder>() {
    override fun getItemCount(): Int = dataListWeek.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekFarmAllViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmnew_all, parent, false)
        return WeekFarmAllViewHolder(mainView)
    }

    override fun onBindViewHolder(holderWeek: WeekFarmAllViewHolder, position: Int) {

        Glide.with(holderWeek.itemView.context)
                .load(dataListWeek[position].img)
                .into(holderWeek.picall)
        holderWeek.dateall.text = dataListWeek[position].period
        holderWeek.titleall.text = dataListWeek[position].name
        holderWeek.addressall.text = dataListWeek[position].addr
        holderWeek.priceall.text = dataListWeek[position].price.toString()
        holderWeek.starall.rating = dataListWeek[position].star!!
        if (dataListWeek[position].isBooked != null) {
            when (dataListWeek[position].isBooked) {
                0 -> holderWeek.isBookedall.isSelected = false
                1 -> holderWeek.isBookedall.isSelected = true
            }
        }
        Glide.with(holderWeek.itemView.context)
                .load(dataListWeek[position].farmerImg)
                .into(holderWeek.farmerImgall)

        holderWeek.starNumall.text = dataListWeek[position].star.toString()


        holderWeek.itemView.setOnClickListener {
            val intent = Intent(holderWeek.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("farm_img", dataListWeek[position].img)
            intent.putExtra("farm_location", dataListWeek[position].addr)
            intent.putExtra("farm_price", dataListWeek[position].price)
            intent.putExtra("farm_days", dataListWeek[position].period)
            intent.putExtra("farm_name", dataListWeek[position].name)

            //추천수
            //설명
            holderWeek.itemView.context.startActivity(intent)
        }
    }
}