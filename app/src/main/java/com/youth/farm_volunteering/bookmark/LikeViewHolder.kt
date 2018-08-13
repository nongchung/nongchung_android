package com.youth.farm_volunteering.bookmark

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class LikeViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var price : TextView = itemView!!.findViewById<TextView>(R.id.item_like_price) as TextView
    var img : ImageView = itemView!!.findViewById<ImageView>(R.id.item_like_iv) as ImageView
    var name : TextView = itemView!!.findViewById<TextView>(R.id.item_like_title) as TextView
    var addr : TextView = itemView!!.findViewById<TextView>(R.id.item_like_location) as TextView

}