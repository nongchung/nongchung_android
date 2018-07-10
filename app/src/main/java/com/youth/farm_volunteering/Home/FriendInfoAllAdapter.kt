package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.Home.FriendInfoAdapter.Companion.friendsizelist
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.FriendInfoData

//참석자 image를 나오게 하는거라고 생각하고있음!
class FriendInfoAllAdapter(var dataList: List<FriendInfoData>) : RecyclerView.Adapter<FriendInfoAllViewHolder>() {

    override fun getItemCount(): Int = dataList.size

    //private lateinit var onIntroClick: View.OnClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendInfoAllViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_allfriendinfo, parent, false)
        //mainView.setOnClickListener(onIntroClick)
        return FriendInfoAllViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: FriendInfoAllViewHolder?, position: Int) {


        Glide.with(holder!!.itemView.context)
                .load(dataList[position].img)
                .into(holder.FriendAllimage)
        holder.FriendAllname.text  = dataList[position].name



    }




}


