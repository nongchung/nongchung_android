package com.youth.farm_volunteering

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.data.NonghwalData

class FarmAdapter(var dataList : List<NonghwalData>) : RecyclerView.Adapter<FarmItemViewHolder>(){
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmItemViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farm, parent, false)
        return FarmItemViewHolder(mainView)
    }


    override fun onBindViewHolder(holder: FarmItemViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
                .load(dataList[position].img)
                .into(holder.pic)
        holder.date.text = dataList[position].period
        holder.title.text = dataList[position].name
        holder.address.text = dataList[position].addr
        holder.price.text = dataList[position].price.toString()

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("farm_img", dataList[position].img)
            intent.putExtra("farm_location", dataList[position].addr)
            intent.putExtra("farm_price", dataList[position].price)
            intent.putExtra("farm_days", dataList[position].period)
            intent.putExtra("farm_name", dataList[position].name)

            //추천수
            //설명
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