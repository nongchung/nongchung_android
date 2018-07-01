package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R

class FarmRecyAdapter(private var recyitems : ArrayList<FarmRecyData>) : RecyclerView.Adapter<FarmViewHolder>(){
    private lateinit var onItemClick: View.OnClickListener

    override fun getItemCount(): Int = recyitems.size
    fun setOnItemClickListener(l : View.OnClickListener){

        onItemClick = l;

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FarmViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.activity_recycle_item,parent, false)

        mainView.setOnClickListener(onItemClick)
        return FarmViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: FarmViewHolder?, position: Int) {

        holder!!.FarmProfile.setImageResource(recyitems[position].farmprofile)
        holder!!.FarmTitle.text = recyitems[position].farmtitle
        holder!!.FarmDate.text = recyitems[position].farmdate
        holder!!.FarmAddress.text = recyitems[position].farmaddress
        holder!!.FarmPrice.text = recyitems[position].farmprice
    }
}

