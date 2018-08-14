package com.youth.farm_volunteering.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class FriendInfoViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var Friendimage : ImageView = itemView!!.findViewById<ImageView>(R.id.farm_friendinfo_img) as ImageView
    var Friendname : TextView = itemView!!.findViewById<TextView>(R.id.farm_friendinfo_name) as TextView
}