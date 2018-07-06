package com.youth.farm_volunteering.Bookmark

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.youth.farm_volunteering.R

class LikeAdapter (var LikeItems : ArrayList<LikeData>) : RecyclerView.Adapter<LikeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_like, parent, false)
        return LikeViewHolder(mainView)
    }

    override fun getItemCount(): Int = LikeItems.size

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        holder.picture.setImageResource(LikeItems[position].picture)
        holder.title.text = LikeItems[position].title
        holder.location.text = LikeItems[position].location
        holder.price.text = LikeItems[position].price

        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context, "눌러쪙? >_<", Toast.LENGTH_SHORT).show()
        }
    }
}