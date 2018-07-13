package com.youth.farm_volunteering.Home.Schedule

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R

class ScheduleAdapter(var dataList: List<DetailSchData>) : RecyclerView.Adapter<ScheduleViewHolder>() {
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ScheduleViewHolder {
        val mainView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_schedule, parent, false)
        //mainView.setOnClickListener(onItemClick)
        return ScheduleViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder?, position: Int) {
        holder!!.textviewTime.text = dataList[position].time
        holder!!.textviewActivity.text = dataList[position].activity

        if(position == dataList.size-1)
        {
            holder.imageviewLine.visibility = View.GONE
        }
    }
}