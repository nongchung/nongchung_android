package com.youth.farm_volunteering

import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.data.HomeNonghwalData

class WeekFarmAdapter(var dataListHome: List<HomeNonghwalData>) : RecyclerView.Adapter<WeekFarmItemViewHolder>() {
    override fun getItemCount(): Int = dataListHome.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekFarmItemViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farm, parent, false)
        return WeekFarmItemViewHolder(mainView)
    }

    override fun onBindViewHolder(holderWeek: WeekFarmItemViewHolder, position: Int) {

        Glide.with(holderWeek.itemView.context)
                .load(dataListHome[position].img)
                .into(holderWeek.pic)
        holderWeek.date.text = dataListHome[position].period
        holderWeek.title.text = dataListHome[position].name
        holderWeek.address.text = dataListHome[position].addr
        holderWeek.price.text = dataListHome[position].price.toString()
        holderWeek.star.rating = dataListHome[position].star!!.toFloat()
        if (dataListHome[position].isBooked != null) {
            when (dataListHome[position].isBooked) {
                0 -> holderWeek.isBooked.isSelected = false
                1 -> holderWeek.isBooked.isSelected = true
            }
        }
        holderWeek.starNum.text = dataListHome[position].star.toString()


        holderWeek.itemView.setOnClickListener {
            val intent = Intent(holderWeek.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("populData", dataListHome[position] as Parcelable)


            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

            //추천수
            //설명
            holderWeek.itemView.context.startActivity(intent)
        }
    }


//
//    var onItemClick : View.OnClickListener? = null
//
//    override fun getItemCount(): Int = dataListHome.size
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