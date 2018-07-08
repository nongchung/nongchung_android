package com.youth.farm_volunteering

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.youth.farm_volunteering.R.id.detail_date_btn
import com.youth.farm_volunteering.R.id.parent
import com.youth.farm_volunteering.data.DetailApplyData
import kotlinx.android.synthetic.main.activity_farm_detail.*
import kotlinx.android.synthetic.main.activity_farm_detail.view.*
import java.security.AccessController.getContext
import android.widget.CheckBox
import com.youth.farm_volunteering.R.id.view




class DetailApplyAdapter(var items: ArrayList<DetailApplyData>) : RecyclerView.Adapter<DetailApplyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailApplyViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_apply, parent, false)
        return DetailApplyViewHolder(mainView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holderDetail: DetailApplyViewHolder, position: Int) {

        holderDetail.schedule.text = items[position].apply_rv_schedule
        holderDetail.start.text = items[position].apply_rv_start
        holderDetail.attendable.text = items[position].apply_rv_attendable
        holderDetail.left.text = items[position].apply_rv_left

        holderDetail.itemView.setOnClickListener {

            val intent = Intent(holderDetail.itemView.context, FarmDetailActivity::class.java)

            intent.putExtra("date",items[position].apply_rv_schedule)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            holderDetail.itemView.context.startActivity(intent)
        }

    }
}