package com.youth.farm_volunteering.myactivity

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.youth.farm_volunteering.R

class MyactivityViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView) {

    var startDate: TextView = itemView!!.findViewById<TextView>(R.id.my_startdate) as TextView
    var endDate: TextView = itemView!!.findViewById<TextView>(R.id.my_enddate) as TextView
    var addr: TextView = itemView!!.findViewById<TextView>(R.id.my_place) as TextView
    var name: TextView = itemView!!.findViewById<TextView>(R.id.my_nonghwalname) as TextView
  //  var state: TextView = itemView!!.findViewById(R.id.my_enddate) 신청중 신청완료 버튼
    var price: TextView = itemView!!.findViewById(R.id.my_price)
    var currentPerson : TextView = itemView!!.findViewById(R.id.my_peoplecount)
    var constraintLayoutApplyIng : ConstraintLayout = itemView!!.findViewById(R.id.constraintLayout_apply_ing)
    var textviewWrieteReview : TextView = itemView!!.findViewById(R.id.textview_write_review)
     var person : TextView = itemView!!.findViewById(R.id.my_people)
     var personLimit : TextView = itemView!!.findViewById(R.id.my_people_limit)
    //var idx : TextView = itemView!!.findViewById(R.id.my_people)
    var img : ImageView = itemView!!.findViewById(R.id.my_image)
    var changeimg : ImageView = itemView!!.findViewById(R.id.textview_apply_ing)
    var changemoney : ImageView = itemView!!.findViewById(R.id.textview_deposit_state)
    var changebar : ProgressBar = itemView!!.findViewById(R.id.progressBar_blue)
    var canceltext : TextView = itemView!!.findViewById(R.id.cancel_text)
    //var rState : TextView = itemView!!.findViewById(R.id.my_people) 후기작성및 수정버튼활성화
   // var rIdx : TextView = itemView!!.findViewById(R.id.my_people)
}