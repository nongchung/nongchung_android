package com.youth.farm_volunteering.Bookmark

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.LikeData

class LikeAdapter (var LikeItems : List<LikeData>) : RecyclerView.Adapter<LikeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_like, parent, false)
        return LikeViewHolder(mainView)
    }

    override fun getItemCount(): Int = LikeItems.size

    override fun onBindViewHolder(holder: LikeViewHolder, position: Int) {
        Glide.with(holder!!.itemView.context)
                .load(LikeItems[position].img)
                .into(holder.img)
        holder.name.text = LikeItems[position].name
        holder.addr.text = LikeItems[position].addr
        holder.price.text = (LikeItems[position].price).toString()

//        holder.itemView.setOnClickListener{
//            Toast.makeText(holder.itemView.context, "눌러쪙? >_<", Toast.LENGTH_SHORT).show()
//        }
    }
}