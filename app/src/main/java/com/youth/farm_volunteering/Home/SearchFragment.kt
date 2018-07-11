package com.youth.farm_volunteering.Home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.fragment_search.*
import com.yongbeom.aircalendar.core.AirCalendarIntent


class SearchFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_search, container, false)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        v.findViewById<View>(R.id.layout_search_date).setOnClickListener {
            val intent = AirCalendarIntent(activity)
            intent.isBooking(false)
            intent.isSelect(false)
//            intent.setBookingDateArray('Array Dates( format: yyyy-MM-dd')
//            intent.setStartDate(yyyy, MM, dd) // int
//            intent.setEndDate(yyyy, MM, dd) // int
            intent.isMonthLabels(false)
            startActivityForResult(intent, 1)
        }
        return v
    }

}