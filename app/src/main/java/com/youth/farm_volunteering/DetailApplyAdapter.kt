package com.youth.farm_volunteering

//import com.youth.farm_volunteering.R.id.detail_date_btn
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.data.DetailApplyData


class DetailApplyAdapter(var items: ArrayList<DetailApplyData>) : RecyclerView.Adapter<DetailApplyViewHolder>() {

    private  var onItemClick : View.OnClickListener? = null

    override fun getItemCount(): Int = items!!.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailApplyViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_apply, parent, false)
        return DetailApplyViewHolder(mainView)
    }


    override fun onBindViewHolder(holderDetail: DetailApplyViewHolder, position: Int) {
        holderDetail.itemView.setTag(position)
        holderDetail.itemView.setOnClickListener(onItemClick)
        holderDetail.schedule.text = items!![position].apply_rv_schedule
        holderDetail.period.text = "(" + items!![position].apply_rv_period + ")"
        holderDetail.start.text = items!![position].apply_rv_start
//        holderDetail.attendable.text = items!![position].apply_rv_attendable
        if(items!![position].apply_rv_attendable.equals(1)){
            holderDetail.attendable.text = "모집마감"
            holderDetail.attendable.setTextColor(Color.parseColor("#8A000000"))
        }else if(items!![position].apply_rv_attendable.equals(0)){
            holderDetail.attendable.text = "참석가능"
        }
        holderDetail.left.text = items!![position].apply_rv_left

    }
    fun setOnItemClickListener(l:View.OnClickListener){
        onItemClick = l
    }
}