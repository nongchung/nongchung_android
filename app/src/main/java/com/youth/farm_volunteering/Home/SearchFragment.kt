package com.youth.farm_volunteering.Home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.fragment_search.*
import com.yongbeom.aircalendar.core.AirCalendarIntent
import com.youth.farm_volunteering.area.AreaSelectActivity


class SearchFragment : Fragment() {
    var registrationUserCount = 1
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
        v.findViewById<View>(R.id.layout_search_area).setOnClickListener {
            val intent = Intent(activity, AreaSelectActivity::class.java)
            startActivity(intent)
        }
        v.findViewById<View>(R.id.activity_search_plusButton).setOnClickListener {
            registrationUserCount = Math.min(9, registrationUserCount + 1)
            v.findViewById<TextView>(R.id.activity_search_participants).setText(registrationUserCount.toString())
        }
        v.findViewById<View>(R.id.activity_search_minusButton).setOnClickListener {
            registrationUserCount = Math.max(1, registrationUserCount - 1)
            v.findViewById<TextView>(R.id.activity_search_participants).setText(registrationUserCount.toString())
        }
        return v
    }

}