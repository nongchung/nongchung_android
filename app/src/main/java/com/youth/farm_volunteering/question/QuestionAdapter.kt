package com.youth.farm_volunteering.question

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.youth.farm_volunteering.R

class QuestionAdapter(private var Questionitems : ArrayList<QuestionData>) : RecyclerView.Adapter<QuestionVIewHolder>() {

    private  lateinit var onItemClick : View.OnClickListener

    override fun getItemCount(): Int = Questionitems.size

    fun setOnItemClickListener(l : View.OnClickListener){

        onItemClick = l;

    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): QuestionVIewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_question,parent, false)

        mainView.setOnClickListener(onItemClick)
        return QuestionVIewHolder(mainView)
    }

    override fun onBindViewHolder(holder: QuestionVIewHolder?, position: Int) {

//        holder!!.QuestionButton.setOnClickListener{
//            //여기서 버튼을 통한 text 설정을 원함
//            Questionitems[position].questionButton
//
//
//        }
        holder!!.QuestionText.text = Questionitems[position].questionText
    }
}
//
//override fun getItemCount(): Int = scheduleitems.size
//fun setOnItemClickListener(l : View.OnClickListener){
//
//    onItemClick = l;
//
//}
//override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScheduleViewHolder {
//    val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_schedule,parent, false)
//
//    mainView.setOnClickListener(onItemClick)
//    return ScheduleViewHolder(mainView)
//}
//override fun onBindViewHolder(holder: ScheduleViewHolder?, position: Int) {
//
//    holder!!.FarmSchedule1.text = scheduleitems[position].schedule1
//    holder!!.FarmSchedule2.text = scheduleitems[position].schedule2
//    holder!!.FarmSchedule3.text = scheduleitems[position].schedule3
//
//}