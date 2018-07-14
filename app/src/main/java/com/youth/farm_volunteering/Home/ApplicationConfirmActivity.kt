package com.youth.farm_volunteering.Home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_application_confirm.*
import java.text.SimpleDateFormat
import java.util.*

class ApplicationConfirmActivity : AppCompatActivity() {

    var getUserName: String? = null
    var getNhAddr: String? = null
    var getNhName: String? = null
    var getNhPrice: String? = null
    var getMax: Int? = null
    var getCur: Int? = null
    var period: String? = null
    var startDate: Date? = null
    var endDate: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_confirm)

        getUserName = intent.getStringExtra("userName")
        getNhAddr = intent.getStringExtra("nhAddr")
        getNhName = intent.getStringExtra("nhName")
        getNhPrice = intent.getStringExtra("nhPrice")
        getMax = intent.getIntExtra("maxPerson", 0)
        getCur = intent.getIntExtra("currentPerson", 0)
        period = intent.getStringExtra("period")
        var startDateString = intent.getStringExtra("start_date")
        var perioidInt = Integer.parseInt(period!!.split("박")[0]) + 1
        var dateFormat = SimpleDateFormat("yyyy-MM-dd")
        startDate = dateFormat.parse(startDateString)
        endDate = Date(startDate!!.time + perioidInt * 1000 * 60 * 60 * 24)

        textViewUserName.setText(getUserName)
        textViewConfirmAddr.setText(getUserName)
        textViewConfirmPrice.setText(getUserName)
        textViewConfirmTitle.setText(getUserName)
        progressBarConfrim.max = getMax!!
        progressBarConfrim.progress = getCur!!
        textViewLeftPersonBottom.setText(getMax!! - getCur!!).toString()
        textViewLeftPersonTop.setText(getMax!! - getCur!!).toString()
        textview_confirm_startmonth.text = (startDate!!.year.toString() + "." + (startDate!!.month + 1))
        textview_confirm_startdate.text = startDate!!.day.toString()
        textview_confirm_endmonth.text = endDate!!.year.toString() + "." + (endDate!!.month + 1)
        textview_confirm_enddate.text = endDate!!.day.toString()

        buttonFinalConfrim.setOnClickListener{
            Toast.makeText(applicationContext, "신청 완료!", Toast.LENGTH_SHORT).show()
        }
    }
}
