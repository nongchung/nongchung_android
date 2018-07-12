package com.youth.farm_volunteering.MyActivity

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var myimage : ImageView = itemView!!.findViewById(R.id.my_image)
    var myname : TextView = itemView!!.findViewById<TextView>(R.id.my_nonghwalname) as TextView
    var myplace : TextView = itemView!!.findViewById<TextView>(R.id.my_place) as TextView
    var myprice : TextView = itemView!!.findViewById<TextView>(R.id.my_price) as TextView
    var mystartdate : TextView = itemView!!.findViewById<TextView>(R.id.my_startdate) as TextView
    var myenddate : TextView = itemView!!.findViewById<TextView>(R.id.my_enddate) as TextView
    var mypeople : TextView = itemView!!.findViewById<TextView>(R.id.my_people) as TextView
    var mypeoplecount : TextView = itemView!!.findViewById<TextView>(R.id.my_peoplecount) as TextView
}