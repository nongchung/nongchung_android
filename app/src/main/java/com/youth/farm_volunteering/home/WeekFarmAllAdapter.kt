package com.youth.farm_volunteering.home

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.youth.farm_volunteering.FarmDetailActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.BookmarkData
import com.youth.farm_volunteering.data.NonghwalData
import com.youth.farm_volunteering.data.PopularSubData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeekFarmAllAdapter(var dataListWeek: List<NonghwalData>) : RecyclerView.Adapter<WeekFarmAllViewHolder>() {
    override fun getItemCount(): Int = dataListWeek.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekFarmAllViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmweek_all, parent, false)
        return WeekFarmAllViewHolder(mainView)
    }

    override fun onBindViewHolder(holderWeek: WeekFarmAllViewHolder, position: Int) {

        Glide.with(holderWeek.itemView.context)
                .load(dataListWeek[position].img)
                .apply(RequestOptions().placeholder(R.drawable.user1_temp)) //이 부분에 띄워줄 로딩화면 구하면 넣어주는게 좋을듯
                .thumbnail(0.1f)
                .into(holderWeek.picall)
        holderWeek.dateall.text = dataListWeek[position].period
        holderWeek.titleall.text = dataListWeek[position].name
        holderWeek.addressall.text = dataListWeek[position].addr
        holderWeek.priceall.text = dataListWeek[position].price.toString()
        holderWeek.starall.rating = dataListWeek[position].star!!.toFloat() // toFloat생성
        if (dataListWeek[position].isBooked != null) {
            when (dataListWeek[position].isBooked) {
                0 -> holderWeek.isBookedall.isSelected = false
                1 -> holderWeek.isBookedall.isSelected = true
            }
        }
        holderWeek.starNumall.text = dataListWeek[position].star.toString()

        holderWeek.isBookedall.setOnClickListener {
            if (dataListWeek[position].isBooked == 0) {
                var bookMark = ApplicationController.instance!!.networkService!!.bookMark(Integer.parseInt(dataListWeek[position].getRealId().toString()))
                bookMark.enqueue(object : Callback<BookmarkData> {
                    override fun onFailure(call: Call<BookmarkData>?, t: Throwable?) {
                        Toast.makeText(holderWeek.itemView.context, "bookmark request fail", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<BookmarkData>?, response: Response<BookmarkData>?) {
                        if (response!!.body().message == "Success to Add") {
                            holderWeek.isBookedall.isSelected = true
                            dataListWeek[position].isBooked = 1
                        } else if (response!!.body().message == "Already Exist") {
                            Toast.makeText(holderWeek.itemView.context, "이미 북마크에 저장하였습니다", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }

            if (dataListWeek[position].isBooked == 1) {
                var delete = ApplicationController.instance!!.networkService!!.delete(Integer.parseInt(dataListWeek[position].getRealId().toString()))
                delete.enqueue(object : Callback<BookmarkData> {
                    override fun onFailure(call: Call<BookmarkData>?, t: Throwable?) {
                        Toast.makeText(holderWeek.itemView.context, "bookmark request fail", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<BookmarkData>?, response: Response<BookmarkData>?) {
                        if (response!!.body().message == "Success to Delete") {
                            holderWeek.isBookedall.isSelected = false
                            dataListWeek[position].isBooked = 0
                        } else if (response!!.body().message == "No nonghwal activity") {
                            Toast.makeText(holderWeek.itemView.context, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(holderWeek.itemView.context, response!!.body().message, Toast.LENGTH_SHORT).show()
                        }

                    }
                })


            }
        }


        holderWeek.itemView.setOnClickListener {
            val intent = Intent(holderWeek.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("populData", dataListWeek[position])
            intent.putExtra("is_from_showall", true)

            //추천수
            //설명
            holderWeek.itemView.context.startActivity(intent)
        }
    }
}