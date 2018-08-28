package com.youth.farm_volunteering.home.FarmProfile

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.FarmerProfileData
import com.youth.farm_volunteering.R.id.imageView
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import com.bumptech.glide.Glide


class FarmProfileAdapter(var profileList: List<FarmerProfileData>) : RecyclerView.Adapter<FarmProfileViewHolder>() {
    override fun getItemCount(): Int = profileList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmProfileViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmprofile, parent, false)
        return FarmProfileViewHolder(mainView)
    }

    override fun onBindViewHolder(holderWeek: FarmProfileViewHolder, position: Int) {
//
        Glide.with(holderWeek.itemView.context)
                .load(profileList[position].farmImg)
                .into(holderWeek.profilefarmImg)


//        holderWeek.profileisBooked.text = profileList[position].isBooked.toString()
        holderWeek.profilenhName.text = profileList[position].nhName
        holderWeek.profileperiod.text = profileList[position].period
        holderWeek.profileprice.text = profileList[position].price.toString()
        holderWeek.profiledatafarmImg.text = profileList[position].period
//        holderWeek.profileplace.text = profileList[position].place



    }
}