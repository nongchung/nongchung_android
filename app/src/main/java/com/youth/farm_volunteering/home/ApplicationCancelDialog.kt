package com.youth.farm_volunteering.home

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.CancellationSignal
import android.view.*
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.dialog_application_cancel.*

class ApplicationCancelDialog : Dialog {

    var refundListener : View.OnClickListener
    var backToDetailListener : View.OnClickListener

    var refundAddr : String? = null
    var refundName : String? = null
    var startMonth : String? = null
    var endMonth : String? = null
    var startDay : String? = null
    var endDay : String? = null

    constructor(context: Context, refundListener : View.OnClickListener, backToDetailListener: View.OnClickListener,
                refundAddr : String, refundName : String, startMonth : String, endMonth : String, startDay : String, endDay : String) : super(context){
        this.refundListener = refundListener
        this.backToDetailListener = backToDetailListener
        this.refundAddr = refundAddr
        this.refundName = refundName
        this.startMonth = startMonth
        this.endMonth = endMonth
        this.startDay = startDay
        this.endDay = endDay
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        window!!.attributes = lpWindow

        setContentView(R.layout.dialog_application_cancel)

        textview_refund_addr.text = refundAddr
        textview_refund_nhname.text = refundName
        textview_refund_startmonth.text = startMonth
        textview_refund_startday.text = startDay
        textview_refund_endmonth.text = endMonth
        textview_refund_endday.text = endDay

        if((refundListener!= null) and (backToDetailListener!=null)){
            button_apply_refund.setOnClickListener(refundListener)
            button_apply_cancel.setOnClickListener(backToDetailListener)
        }
    }
}