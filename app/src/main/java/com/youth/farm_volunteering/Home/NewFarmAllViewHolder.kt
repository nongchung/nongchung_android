package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class NewFarmAllViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var imageviewNewFarmall : ImageView = itemView!!.findViewById<ImageView>(R.id.imageviewNewFarm_all) as ImageView
    var textviewNewFarmDateall : TextView = itemView!!.findViewById<TextView>(R.id.textviewNewFarmDate_all) as TextView
    var textviewNewFarmTitleall : TextView = itemView!!.findViewById<TextView>(R.id.textviewNewFarmTitle_all) as TextView
    var textviewNewFarmAddrall : TextView = itemView!!.findViewById<TextView>(R.id.textviewNewFarmAddr_all) as TextView
    var textviewNewFarmPriceall : TextView = itemView!!.findViewById<TextView>(R.id.textviewNewFarmPrice_all) as TextView
    var imageviewNewFarmBookmarkall : ImageView = itemView!!.findViewById(R.id.imageviewNewFarmBookmark_all)
}