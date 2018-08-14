package com.youth.farm_volunteering.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class PopulFarmItemViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var imageviewPopulFarmFarm : ImageView = itemView!!.findViewById(R.id.imageviewPopulFarmFarm)
    var imageviewPopulFarmFarmer : ImageView = itemView!!.findViewById(R.id.imageviewPopulFarmFarmer)
    var textviewPopulFarmTitle : TextView = itemView!!.findViewById(R.id.textviewPopulFarmTitle)
    var textviewPopulFarmAddr : TextView = itemView!!.findViewById(R.id.textviewPopulFarmAddr)

}