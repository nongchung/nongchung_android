package com.youth.farm_volunteering.Home.ThemaNonghwal

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class ThemaViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)  {
    var themanhName : TextView = itemView!!.findViewById<TextView>(R.id.farm_title_profile) as TextView
    var themaPeriod : TextView = itemView!!.findViewById<TextView>(R.id.farm_profile_period) as TextView
    var themaAddress : TextView = itemView!!.findViewById<TextView>(R.id.farm_address_profile) as TextView
    var themaPrice : TextView = itemView!!.findViewById<TextView>(R.id.farm_price_profile) as TextView
    var themaImage : ImageView = itemView!!.findViewById<ImageView>(R.id.farm_profile) as ImageView
    var themaDate : TextView = itemView!!.findViewById<ImageView>(R.id.farm_date_profile) as TextView
}