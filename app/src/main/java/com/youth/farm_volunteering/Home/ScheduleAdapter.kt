package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.DetailSchData

class ScheduleAdapter(var dataList : List<DetailSchData>) : RecyclerView.Adapter<ScheduleViewHolder>() {
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_schedule, parent, false)
        //mainView.setOnClickListener(onIntroClick)
        return ScheduleViewHolder(mainView)


        }

        override fun onBindViewHolder(holder: ScheduleViewHolder?, position: Int) {
            holder!!.Scheduletime.text = dataList[position].time
            holder!!.Scheduleactvity.text = dataList[position].activity


        }
    }
