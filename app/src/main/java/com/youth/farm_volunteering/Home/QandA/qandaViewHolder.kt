package com.youth.farm_volunteering.Home.QandA

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class qandaViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var qandaQuestion : TextView = itemView!!.findViewById<TextView>(R.id.question_button) as TextView
    var qandaAnswer : TextView = itemView!!.findViewById<TextView>(R.id.answer_text) as TextView

}