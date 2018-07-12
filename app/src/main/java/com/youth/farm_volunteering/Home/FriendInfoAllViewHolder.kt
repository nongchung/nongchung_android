package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class FriendInfoAllViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var FriendAllimage : ImageView = itemView!!.findViewById<ImageView>(R.id.farm_friendinfoall_img) as ImageView
    var FriendAllname : TextView = itemView!!.findViewById<TextView>(R.id.farm_friendinfoall_name) as TextView
}