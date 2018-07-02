package com.youth.farm_volunteering

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class FarmItemViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var pic : ImageView = itemView!!.findViewById<ImageView>(R.id.farm_pic) as ImageView
    var date : TextView = itemView!!.findViewById<TextView>(R.id.farm_date) as TextView
    var title : TextView = itemView!!.findViewById<TextView>(R.id.farm_title) as TextView
    var address : TextView = itemView!!.findViewById<TextView>(R.id.farm_address) as TextView
    var price : TextView = itemView!!.findViewById<TextView>(R.id.farm_price) as TextView

}