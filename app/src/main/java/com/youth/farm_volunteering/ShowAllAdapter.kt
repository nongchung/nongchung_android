package com.youth.farm_volunteering

import android.support.v7.widget.RecyclerView
import com.youth.farm_volunteering.data.ShowAllResponseData
import android.view.ViewGroup
import android.view.View
import android.view.LayoutInflater
import com.bumptech.glide.Glide

class ShowAllAdapter(var items : ArrayList<ShowAllResponseData>) : RecyclerView.Adapter<ShowAllViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowAllViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_show_all, parent, false)
        return ShowAllViewHolder(mainView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ShowAllViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(items[position].farm_photo)
                .into(holder.farmPhoto)

        Glide.with(holder.itemView.context)
                .load(items[position].farmer_photo)
                .into(holder.farmerPhoto)

        holder.title.text = items[position].title
        holder.location.text = items[position].location
        holder.price.text = items[position].price
    }
}