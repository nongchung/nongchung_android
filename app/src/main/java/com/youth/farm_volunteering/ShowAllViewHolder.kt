package com.youth.farm_volunteering

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class ShowAllViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var farmPhoto : ImageView = itemView!!.findViewById<ImageView>(R.id.show_all_farm_photo) as ImageView
    var farmerPhoto : ImageView = itemView!!.findViewById<ImageView>(R.id.show_all_farmer_photo) as ImageView
    var title : TextView = itemView!!.findViewById<TextView>(R.id.show_all_title) as TextView
    var location : TextView = itemView!!.findViewById<TextView>(R.id.show_all_location) as TextView
    var price : TextView = itemView!!.findViewById<TextView>(R.id.show_all_price) as TextView

}