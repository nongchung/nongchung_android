package com.youth.farm_volunteering.Home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.youth.farm_volunteering.FarmDetailActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.AllStData
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
    var getStData : AllStData? = null
    var period: String? = null
    var parsedStartDate: Date? = null
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
        getStData = intent.getParcelableExtra("selectedStData")

        var start : String? = getStData!!.startDate
        var end : String? = getStData!!.endDate

        period = intent.getStringExtra("period")
//        var startDateString = intent.getStringExtra("start_date")
//        var perioidInt = Integer.parseInt(period!!.split("박")[0]) + 1
//        parsedStartDate = dateFormat.parse(startDateString)
        var dateFormat = SimpleDateFormat("yyyy-MM-dd")
        var dateDotFormat = SimpleDateFormat("yyyy.MM.dd")

        var parsedStart = dateFormat.parse(start)
        var parsedEnd = dateFormat.parse(end)

        var formattedStart = dateDotFormat.format(parsedStart)
        var formattedEnd = dateDotFormat.format(parsedEnd)

        var startArr : List<String> = formattedStart.split(".")
        var EndArr : List<String> = formattedEnd.split(".")

//        endDate = Date(parsedStartDate!!.time + perioidInt * 1000 * 60 * 60 * 24)

        textViewUserName.setText(getUserName)
        textViewConfirmAddr.setText(getNhAddr)
        textViewConfirmPrice.setText(getNhPrice)
        textViewConfirmTitle.setText(getNhName)
        progressBarConfrim.max = getMax!!
        progressBarConfrim.progress = getCur!!

        var getLeftPeople : Int = getMax!!-getCur!!

        textview_left_bottom.text = getLeftPeople.toString() + "분"
        textViewLeftPersonTop.text = getLeftPeople.toString() + " 명"
        textview_confirm_startmonth.text = startArr[0] + "." + startArr[1]
        textview_confirm_startdate.text = startArr[2]
        textview_confirm_endmonth.text = EndArr[0] + "." + EndArr[1]
        textview_confirm_enddate.text = EndArr[2]
        textview_progress_people.text = getCur.toString() + "/" + getMax.toString()

        buttonFinalConfrim.setOnClickListener{
            setResult(FarmDetailActivity.applyReqCode)
            Toast.makeText(applicationContext, "신청 완료!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
