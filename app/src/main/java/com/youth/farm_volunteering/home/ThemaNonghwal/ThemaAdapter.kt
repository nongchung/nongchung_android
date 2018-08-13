package com.youth.farm_volunteering.home.ThemaNonghwal

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.youth.farm_volunteering.FarmDetailActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.StartActivity
import com.youth.farm_volunteering.data.NonghwalData

class ThemaAdapter(var themaList: List<ThemaListData>) : RecyclerView.Adapter<ThemaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ThemaViewHolder {
        val mainView: View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.item_thema, parent, false)
        return ThemaViewHolder(mainView)
    }

    override fun getItemCount(): Int = themaList.size

    override fun onBindViewHolder(holder: ThemaViewHolder?, position: Int) {
        Glide.with(holder!!.itemView.context)
                .load(themaList[position].fImg)
                .apply(RequestOptions().placeholder(R.drawable.user1_temp))
                .into(holder.themaImage)
        holder.themanhName.text = themaList[position].name
        holder.themaPeriod.text = themaList[position].period
        holder.themaAddress.text = themaList[position].addr
        holder.themaPrice.text = themaList[position].price.toString()
        holder.themaDate.text = themaList[position].period
        when(themaList[position].newState){
            0-> holder.themaNewstate.visibility = View.GONE
            1-> holder.themaNewstate.visibility = View.VISIBLE
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("populData",themaList[position])
            intent.putExtra("is_from_theme", true)

            holder.itemView.context.startActivity(intent)
        }
    }
}