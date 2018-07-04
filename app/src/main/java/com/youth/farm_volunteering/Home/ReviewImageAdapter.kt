package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R

class ReviewImageAdapter(private var ReviewImageitems : ArrayList<ReviewImageData>) : RecyclerView.Adapter<ReviewImageViewHolder>(){

    private lateinit var onItemClick: View.OnClickListener

    override fun getItemCount(): Int = ReviewImageitems.size
    fun setOnItemClickListener(l : View.OnClickListener){

        onItemClick = l;

    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReviewImageViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_schedule,parent, false)

        mainView.setOnClickListener(onItemClick)
        return ReviewImageViewHolder(mainView)
    }
    override fun onBindViewHolder(holder: ReviewImageViewHolder?, position: Int) {

        holder!!.FarmBoxReviewImg.setImageResource(ReviewImageitems[position].reviewBoxImg)


    }
}

//
//class ScheduleAdapter(private var scheduleitems : ArrayList<ScheduleData>) : RecyclerView.Adapter<ScheduleViewHolder>(){
//
//    private lateinit var onItemClick: View.OnClickListener
//
//    override fun getItemCount(): Int = scheduleitems.size
//    fun setOnItemClickListener(l : View.OnClickListener){
//
//        onItemClick = l;
//
//    }
//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScheduleViewHolder {
//        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_schedule,parent, false)
//
//        mainView.setOnClickListener(onItemClick)
//        return ScheduleViewHolder(mainView)
//    }
//    override fun onBindViewHolder(holder: ScheduleViewHolder?, position: Int) {
//
//        holder!!.FarmSchedule1.text = scheduleitems[position].schedule1
//        holder!!.FarmSchedule2.text = scheduleitems[position].schedule2
//        holder!!.FarmSchedule3.text = scheduleitems[position].schedule3
//
//    }
//
//}