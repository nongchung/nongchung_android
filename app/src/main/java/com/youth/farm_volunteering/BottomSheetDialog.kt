package com.youth.farm_volunteering

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.Home.Schedule.DetailSchData
import com.youth.farm_volunteering.data.AllStData
import com.youth.farm_volunteering.data.ApplyRvData

/**
 * Created by Lee_wonjun on 2018-07-12.
 */
class BottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {

    var applyList : ArrayList<ApplyRvData>? = null
    var getDetailScheduleList: ArrayList<DetailSchData>? = null
    var getDetailNearestStartDate : String? = null
    var getDetailAllStartDate : ArrayList<AllStData>? = null
    var getDetailMyScheduleActivities : ArrayList<Int>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.bottom_sheet_dialog, container, false)

        getDetailScheduleList = arguments.getParcelableArrayList("scheList")
        getDetailMyScheduleActivities = arguments.getIntegerArrayList("myScheduleActivities")
        getDetailAllStartDate = arguments.getParcelableArrayList("allStartItems")




//        recyclerviewBottomDialog.adapter



        return v
    }

    override fun onClick(v: View?) {

    }

    companion object{
        var instance : BottomSheetDialog? = BottomSheetDialog()
    }
}