package com.youth.farm_volunteering.Bookmark

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class LikeViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    var picture : ImageView = itemView!!.findViewById<ImageView>(R.id.item_like_iv) as ImageView
    var title : TextView = itemView!!.findViewById<TextView>(R.id.item_like_title) as TextView
    var location : TextView = itemView!!.findViewById<TextView>(R.id.item_like_location) as TextView
    var price : TextView = itemView!!.findViewById<TextView>(R.id.item_like_price) as TextView
}