package com.youth.farm_volunteering

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.home.Schedule.DetailSchData
import com.youth.farm_volunteering.data.AllStData
import com.youth.farm_volunteering.data.DetailApplyData
import com.youth.farm_volunteering.data.FriendInfoData
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Lee_wonjun on 2018-07-12.
 */
class BottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {

    var selectedChildIdx: Int? = null
    var selectedDate: String? = null
    var selectedIdx : Int? = null
    var selectedStData : AllStData? = null

    var onDismissListener: DialogInterface.OnDismissListener? = null
    var applyList: ArrayList<DetailApplyData>? = null
    var getDetailScheduleList: ArrayList<DetailSchData>? = null
    var getDetailAllStartDate: ArrayList<AllStData>? = null
    var getDetailFriendInfoList: ArrayList<FriendInfoData>? = null
    var getDetailMyScheduleActivities: ArrayList<Int>? = null
    var getDetailNearestStartDate : String? = null

    var parsedStartDate : Date? = null
    var parsedEndDate : Date? = null
    var StartDate : String? = null
    var EndDate : String? = null

    var dateClickListener: View.OnClickListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.bottom_sheet_dialog, container, false)

        var applyAdapter: DetailApplyAdapter? = null

        getDetailScheduleList = arguments.getParcelableArrayList("scheList")
        getDetailMyScheduleActivities = arguments.getIntegerArrayList("myScheduleActivities")
        getDetailAllStartDate = arguments.getParcelableArrayList("allStartItems")
        getDetailNearestStartDate = arguments.getString("nearestStartDate")

        var dateFormat = SimpleDateFormat("yyyy-MM-dd")
        var dateFormatStart = SimpleDateFormat("yyyy년 MM월 dd일")
        var dateFormatEnd = SimpleDateFormat(" ~ dd일")

//        applyList = Arrays.asList(arrayOfNulls<AllStData>(getDetailAllStartDate!!.size))

        applyList = ArrayList();
        for (i in 0..getDetailAllStartDate!!.size - 1) {
            parsedStartDate = dateFormat.parse(getDetailAllStartDate!![i].startDate)
            parsedEndDate = dateFormat.parse(getDetailAllStartDate!![i].endDate)

            StartDate = dateFormatStart.format(parsedStartDate)
            EndDate = dateFormatEnd.format(parsedEndDate)

            applyList!!.add(i, DetailApplyData(
                    StartDate + EndDate,
                    getDetailScheduleList!![0].time + getDetailScheduleList!![0].activity!!,
                    getDetailAllStartDate!![i].period!!,
                    getDetailAllStartDate!![i].state!!,
                    "(" + getDetailAllStartDate!![i].availPerson!!.toString() + "명남음)"))
        }
        v.recyclerviewBottomDialog.layoutManager = LinearLayoutManager(this.context)

        applyAdapter = DetailApplyAdapter(applyList!!)
        applyAdapter.setOnItemClickListener(this)

        v.recyclerviewBottomDialog.adapter = applyAdapter

        return v
    }

    override fun onClick(v: View?) {
        selectedChildIdx = v!!.getTag() as Int
        selectedDate  = applyList!!.get(selectedChildIdx!!).apply_rv_schedule
        selectedIdx = getDetailAllStartDate!![selectedChildIdx!!].idx
        selectedStData = getDetailAllStartDate!![selectedChildIdx!!]

        dismiss()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        if (onDismissListener != null) {
            onDismissListener!!.onDismiss(dialog)
        }
    }


    companion object {
        var instance: BottomSheetDialog = BottomSheetDialog()
    }
}