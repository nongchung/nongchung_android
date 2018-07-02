package com.youth.farm_volunteering

import android.content.Intent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class FarmAdapter(var dataList : ArrayList<FarmData>) : RecyclerView.Adapter<FarmItemViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmItemViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farm, parent, false)
        return FarmItemViewHolder(mainView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: FarmItemViewHolder, position: Int) {
        holder.pic.setImageResource(dataList[position].farmPicture)
        holder.date.text = dataList[position].farmDays
        holder.title.text = dataList[position].farmName
        holder.address.text = dataList[position].farmLocation
        holder.price.text = dataList[position].farmPrice

        holder.itemView.setOnClickListener{

            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("farm_img", dataList[position].farmPicture)
            intent.putExtra("farm_location", dataList[position].farmLocation)
            intent.putExtra("farm_price", dataList[position].farmPrice)
            intent.putExtra("farm_days", dataList[position].farmDays)
            intent.putExtra("farm_name", dataList[position].farmName)
            holder.itemView.context.startActivity(intent)
        }
    }

//
//    var onItemClick : View.OnClickListener? = null
//
//    override fun getItemCount(): Int = dataList.size
//
//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FarmGridViewHolder {
//        val mainView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_farm, parent, false)
//        mainView.setOnClickListener(onItemClick)
//        return FarmGridViewHolder(mainView)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    fun setOnItemClickListener(l:View.OnClickListener){
//        onItemClick = l
//    }
}