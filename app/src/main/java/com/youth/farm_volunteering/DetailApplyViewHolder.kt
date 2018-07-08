package com.youth.farm_volunteering

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.youth.farm_volunteering.data.DetailApplyData

class DetailApplyViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    

    var schedule : TextView = itemView!!.findViewById<TextView>(R.id.apply_rv_schedule) as TextView
    var start : TextView = itemView!!.findViewById<TextView>(R.id.apply_rv_start) as TextView
    var attendable : TextView = itemView!!.findViewById<TextView>(R.id.apply_rv_attendable) as TextView
    var left : TextView = itemView!!.findViewById<TextView>(R.id.apply_rv_left) as TextView
}