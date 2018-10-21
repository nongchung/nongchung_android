package com.youth.farm_volunteering.search

import android.support.v7.widget.RecyclerView
import android.util.SparseIntArray
import com.youth.farm_volunteering.data.ShowAllResponseData
import android.view.ViewGroup
import android.view.View
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.area.Area
import kotlinx.android.synthetic.main.item_area.view.*

class SelectAreaAdapter(var items: HashMap<Int, Boolean>) : RecyclerView.Adapter<SelectAreaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectAreaViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_area, parent, false)
        return SelectAreaViewHolder(mainView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SelectAreaViewHolder, position: Int) {
        holder.itemView.setTag(position)
        holder.itemView.setOnClickListener { v ->
            items.put(holder.itemView.tag as Int, !(items.get(holder.itemView.tag as Int)!!))
            notifyDataSetChanged()
        }
        var area = enumValues<Area>()[position]

        if (items.get(position)!!) {
            holder.itemView.setBackgroundColor(0xFF3470FF.toInt())
            holder.itemView.textview_area_name.setText(area.regionName)
            holder.itemView.textview_area_name.setTextColor(0xFFFFFFFF.toInt())
        } else {
            holder.itemView.setBackgroundResource(R.drawable.rect)
            holder.itemView.textview_area_name.setText("+ " + area.regionName)
            holder.itemView.textview_area_name.setTextColor(0xFFB1B1B1.toInt())
        }

    }
}