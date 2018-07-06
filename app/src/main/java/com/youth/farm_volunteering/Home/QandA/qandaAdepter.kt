package com.youth.farm_volunteering.Home.QandA

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.item_special.view.*

class qandaAdepter(var qandaItems : ArrayList<qandaItem>) : RecyclerView.Adapter<qandaViewHolder>() {

    private var openedIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): qandaViewHolder {
        val qandaView : View = LayoutInflater.from(parent!!.context)
                .inflate(R.layout.activity_farm_detail_qandaitem, parent, false)
        return qandaViewHolder(qandaView)
    }

    override fun getItemCount(): Int = qandaItems.size

    override fun onBindViewHolder(holder: qandaViewHolder?, position: Int) {
        holder!!.qandaQuestion.text = qandaItems[position].question
        holder!!.qandaAnswer.text = qandaItems[position].answer

        if(position == openedIndex){
            holder.qandaQuestion.setVisibility(View.VISIBLE)
        }else {
            holder.qandaAnswer.setVisibility(View.GONE)
        }
        holder.qandaQuestion.setOnClickListener(View.OnClickListener {
            if (position == openedIndex) {
                openedIndex = -1
            } else {
                openedIndex = position
            }
            notifyDataSetChanged()
        })


    }
}