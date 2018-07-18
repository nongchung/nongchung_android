package com.youth.farm_volunteering.Bookmark

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.BookmarkData
import com.youth.farm_volunteering.data.LikeData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        holder.itemView.setOnClickListener {
            Log.d("idx",(LikeItems[position].idx).toString())

            var delete = ApplicationController.instance!!.networkService!!.delete(Integer.parseInt((LikeItems[position].idx).toString()))
            delete.enqueue(object : Callback<BookmarkData> {
                override fun onFailure(call: Call<BookmarkData>?, t: Throwable?) {
                    Toast.makeText(holder.itemView.context, "bookmark request fail", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<BookmarkData>?, response: Response<BookmarkData>?) {

                    Log.d("aaa",response!!.message())

                    Log.d("aaa", response!!.toString())

                    if(response!!.body().message == null){
                        Toast.makeText(holder.itemView.context, "널", Toast.LENGTH_SHORT).show()
                    }
                    else{

                    }


//                    if (response!!.body().message == "Success to Delete") {
//                        Toast.makeText(holder.itemView.context, "북마크에서 삭제하였습니다", Toast.LENGTH_SHORT).show()
//                    } else if (response!!.body().message == "No nonghwal activity") {
//                        Toast.makeText(holder.itemView.context, "에러", Toast.LENGTH_SHORT).show()
//                    }
//                    else{
//                        Toast.makeText(holder.itemView.context,response!!.body().message,Toast.LENGTH_SHORT).show()
//                    }
                }
            })
        }



    }
}