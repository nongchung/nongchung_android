package com.youth.farm_volunteering.search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class SelectAreaViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var name: TextView = itemView!!.findViewById<TextView>(R.id.textview_area_name) as TextView

}