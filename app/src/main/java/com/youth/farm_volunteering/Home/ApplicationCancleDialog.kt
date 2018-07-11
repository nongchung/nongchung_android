package com.youth.farm_volunteering.Home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*
import com.youth.farm_volunteering.R

class ApplicationCancleDialog(context : Context) : Dialog(context) {
//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val v = inflater!!.inflate(R.layout.dialog_application_cancle, container, false)
//
//
//        return v
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        window!!.attributes = lpWindow

        setContentView(R.layout.dialog_application_cancle)

    }
}