package com.youth.farm_volunteering

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.data.AllStData
import com.youth.farm_volunteering.data.DetailApplyData
import com.youth.farm_volunteering.data.DetailSchData
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*

/**
 * Created by Lee_wonjun on 2018-07-12.
 */
class BottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {

    var selectedIdx: Int? = null
    var selectedDate: String? = null

    var onDismissListener: DialogInterface.OnDismissListener? = null
    var applyList: ArrayList<DetailApplyData>? = null
    var getDetailScheduleList: ArrayList<DetailSchData>? = null
    var getDetailAllStartDate: ArrayList<AllStData>? = null
    var getDetailMyScheduleActivities: ArrayList<Int>? = null
    var getDate: String? = null

    var dateClickListener: View.OnClickListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.bottom_sheet_dialog, container, false)

        var applyAdapter: DetailApplyAdapter? = null

        getDetailScheduleList = arguments.getParcelableArrayList("scheList")
        getDetailMyScheduleActivities = arguments.getIntegerArrayList("myScheduleActivities")
        getDetailAllStartDate = arguments.getParcelableArrayList("allStartItems")


//        applyList = Arrays.asList(arrayOfNulls<AllStData>(getDetailAllStartDate!!.size))

        applyList = ArrayList();
        for (i in 0..getDetailAllStartDate!!.size - 1) {
            applyList!!.add(i, DetailApplyData(
                    getDetailAllStartDate!![i].startDate!!,
                    getDetailScheduleList!![0].time + getDetailScheduleList!![0].activity!!,
                    getDetailAllStartDate!![i].state!!,
                    "(" + getDetailAllStartDate!![i].availPerson!!.toString() + "명남음)"))
        }
        v.recyclerviewBottomDialog.layoutManager = LinearLayoutManager(this.activity)

        applyAdapter = DetailApplyAdapter(applyList!!)
        applyAdapter.setOnItemClickListener(this)

        v.recyclerviewBottomDialog.adapter = applyAdapter

        return v
    }

//    override fun setupDialog(dialog: Dialog?, style: Int) {
//        super.setupDialog(dialog, style)
//
//        var contentView : View = View.inflate(this.activity.applicationContext, R.layout.bottom_sheet_dialog, null)
//        dialog!!.setContentView(contentView)
//    }

    override fun onClick(v: View?) {
        selectedIdx = v!!.getTag() as Int
        selectedDate  = applyList!!.get(selectedIdx!!).apply_rv_schedule

        dismiss()
//        override fun onClick(v: View?) {
//            val idx : Int = webList!!.getChildAdapterPosition(v)
//            val name : String? = webDatas!!.get(idx).webText
//
//            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
//        }
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