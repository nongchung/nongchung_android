package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class NewFarmItemViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var imageviewNewFarm : ImageView = itemView!!.findViewById<ImageView>(R.id.imageviewNewFarm) as ImageView
    var textviewNewFarmDate : TextView = itemView!!.findViewById<TextView>(R.id.textviewNewFarmDate) as TextView
    var textviewNewFarmTitle : TextView = itemView!!.findViewById<TextView>(R.id.textviewNewFarmTitle) as TextView
    var textviewNewFarmAddr : TextView = itemView!!.findViewById<TextView>(R.id.textviewNewFarmAddr) as TextView
    var textviewNewFarmPrice : TextView = itemView!!.findViewById<TextView>(R.id.textviewNewFarmPrice) as TextView
    var imageviewNewFarmBookmark : ImageView = itemView!!.findViewById(R.id.imageviewNewFarmBookmark)
}