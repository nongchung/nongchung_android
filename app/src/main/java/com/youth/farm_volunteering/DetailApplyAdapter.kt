package com.youth.farm_volunteering

//import com.youth.farm_volunteering.R.id.detail_date_btn
import android.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.Home.Schedule.DetailSchData
import com.youth.farm_volunteering.data.AllStData
import com.youth.farm_volunteering.data.DetailApplyData


class DetailApplyAdapter(items: ArrayList<DetailApplyData>, fm: FragmentManager) : RecyclerView.Adapter<DetailApplyViewHolder>() {

    var items : ArrayList<DetailApplyData>? = null
    var scheduleItems : ArrayList<DetailSchData>? = null
    var nearestStartDate : String? = null
    var allStartDateItems : ArrayList<AllStData>? = null
    var myScheduleActivities : List<Int>? = null

    constructor(items : ArrayList<DetailApplyData>, scheduleItems : ArrayList<DetailSchData>, nearestStartDate : String?,
                allStartDateItems : ArrayList<AllStData>, myScheduleActivities : List<Int>, fm : FragmentManager) : this(items, fm)
    {
        this.items = items
        this.scheduleItems = scheduleItems
        this.nearestStartDate = nearestStartDate
        this.allStartDateItems = allStartDateItems
        this.myScheduleActivities = myScheduleActivities
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailApplyViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_apply, parent, false)
        return DetailApplyViewHolder(mainView)
    }

    override fun getItemCount(): Int = items!!.size

    override fun onBindViewHolder(holderDetail: DetailApplyViewHolder, position: Int) {

        holderDetail.schedule.text = items!![position].apply_rv_schedule
        holderDetail.start.text = items!![position].apply_rv_start
        holderDetail.attendable.text = items!![position].apply_rv_attendable
        holderDetail.left.text = items!![position].apply_rv_left

        holderDetail.itemView.setOnClickListener {


        }

    }
}