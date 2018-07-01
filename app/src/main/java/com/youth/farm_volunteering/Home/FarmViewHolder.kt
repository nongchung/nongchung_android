package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_recycle_item.view.*

class FarmViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var FarmProfile : ImageView = itemView!!.findViewById(R.id.farm_pic)
    var FarmTitle : TextView = itemView!!.findViewById(R.id.farm_title)
    var FarmDate : TextView = itemView!!.findViewById(R.id.farm_date)
    var FarmAddress : TextView = itemView!!.findViewById(R.id.farm_address)
    var FarmPrice : TextView = itemView!!.findViewById(R.id.farm_price)
}