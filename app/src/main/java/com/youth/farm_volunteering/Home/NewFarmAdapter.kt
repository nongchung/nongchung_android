package com.youth.farm_volunteering.Home

import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.FarmDetailActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.HomeNonghwalData

class NewFarmAdapter(var dataList: List<HomeNonghwalData>) : RecyclerView.Adapter<NewFarmItemViewHolder>() {
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewFarmItemViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmnew, parent, false)
        return NewFarmItemViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: NewFarmItemViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(dataList[position].img)
                .into(holder.imageviewNewFarm)
        holder.textviewNewFarmAddr.setText(dataList[position].addr)
        holder.textviewNewFarmDate.setText(dataList[position].period)
        holder.textviewNewFarmPrice.text = dataList[position].price.toString()
        holder.textviewNewFarmTitle.setText(dataList[position].name)

//        holder.imageviewNewFarmBookmark.setOnClickListener {
//
//            var bookMark = ApplicationController.instance!!.networkService!!.bookMark(Integer.parseInt(dataList[position].nhIdx.toString()))
//            bookMark.enqueue(object : Callback<BookmarkData> {
//                override fun onFailure(call: Call<BookmarkData>?, t: Throwable?) {
//                    Toast.makeText(holder.itemView.context, "bookmark request fail", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onResponse(call: Call<BookmarkData>?, response: Response<BookmarkData>?) {
//                    if (response!!.body().message == "Success to Add") {
//                        holder.imageviewNewFarmBookmark.isSelected = true
//                    } else if (response!!.body().message == "Already Exist") {
//                        Toast.makeText(holder.itemView.context, "이미 북마크에 저장하였습니다", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//            })
//        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("populData", dataList[position] as Parcelable)

            //추천수
            //설명
            holder.itemView.context.startActivity(intent)
        }
    }
}