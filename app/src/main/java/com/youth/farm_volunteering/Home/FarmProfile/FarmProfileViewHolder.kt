package com.youth.farm_volunteering.Home.FarmProfile

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class FarmProfileViewHolder (itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var profileprice : TextView = itemView!!.findViewById<TextView>(R.id.thema_price) as TextView
    var profilenhName : TextView = itemView!!.findViewById<TextView>(R.id.thema_address) as TextView
    var profileperiod : TextView = itemView!!.findViewById<TextView>(R.id.farm_profile_period) as TextView
    var profilefarmImg : ImageView = itemView!!.findViewById<ImageView>(R.id.farm_profile) as ImageView
    var profiledatafarmImg : TextView = itemView!!.findViewById<ImageView>(R.id.farm_date_profile) as TextView

//    var profileisBooked : TextView = itemView!!.findViewById<TextView>(R.id.imageViewBookmark_profile) as TextView

}