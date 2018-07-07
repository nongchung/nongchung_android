package com.youth.farm_volunteering

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.data.ApplyRvData
import com.youth.farm_volunteering.data.ShowAllResponseData

class ApplyRvAdapter(var items : ArrayList<ApplyRvData>) : RecyclerView.Adapter<ApplyRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyRvViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_apply, parent, false)
        return ApplyRvViewHolder(mainView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ApplyRvViewHolder, position: Int) {
        holder.schedule.text = items[position].apply_rv_schedule
        holder.start.text = items[position].apply_rv_start
        holder.attendable.text = items[position].apply_rv_attendable
        holder.left.text = items[position].apply_rv_left
    }
}