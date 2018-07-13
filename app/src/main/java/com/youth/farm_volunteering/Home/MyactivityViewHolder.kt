package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.youth.farm_volunteering.R

class MyactivityViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var startDate: TextView = itemView!!.findViewById<TextView>(R.id.my_startdate) as TextView
    var endDate: TextView = itemView!!.findViewById<TextView>(R.id.my_enddate) as TextView
    var addr: TextView = itemView!!.findViewById<TextView>(R.id.my_place) as TextView
 //   var period: TextView = itemView!!.findViewById<TextView>(R.id.my_price) as TextView
    var name: TextView = itemView!!.findViewById<TextView>(R.id.my_nonghwalname) as TextView
  //  var state: TextView = itemView!!.findViewById(R.id.my_enddate) 신청중 신청완료 버튼
    var price: TextView = itemView!!.findViewById(R.id.my_price)
  //  var currentPerson : TextView = itemView!!.findViewById(R.id.my_people) 현제인원받으면 구현계획
  //  var person : TextView = itemView!!.findViewById(R.id.my_people) 남은총인원?
    //var personLimit : TextView = itemView!!.findViewById(R.id.my_people) // 제한된인원
    //var idx : TextView = itemView!!.findViewById(R.id.my_people)
    var img : ImageView = itemView!!.findViewById(R.id.my_image)
    //var rState : TextView = itemView!!.findViewById(R.id.my_people) 후기작성및 수정버튼활성화
   // var rIdx : TextView = itemView!!.findViewById(R.id.my_people)
}