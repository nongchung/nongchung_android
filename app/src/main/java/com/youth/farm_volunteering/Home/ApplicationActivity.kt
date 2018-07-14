package com.youth.farm_volunteering.Home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.UserData
import kotlinx.android.synthetic.main.activity_application.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var userDataList: ArrayList<UserData>? = null
        var getImageUrl: String? = null
        var getPrice: Int? = null
        var getNhIdx: Int? = null
        var getScheIdx: Int? = null
        var currentPerson: Int? = null
        var maxPerson: Int? = null
        var period: String? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        userDataList = intent.getParcelableArrayListExtra("userData")

        getNhIdx = intent.getIntExtra("nhIdx", 0)
        getScheIdx = intent.getIntExtra("scheIdx", 0)


        editTextName.setText(userDataList[0].name)
        editTextEmail.setText(userDataList[0].mail)
        editTextPhone.setText(userDataList[0].hp)
        editTextBirth.setText(userDataList[0].birthYear + " 년" + userDataList[0].birthMonth + " 월" + userDataList[0].birthDay + "일")
        if (userDataList[0].sex == 1) {
            imageviewMan.isSelected = true
        } else if (userDataList[0].sex == 2) {
            imageviewWoman.isSelected = true
        }
        getImageUrl = intent.getStringExtra("nhImg")
        period = intent.getStringExtra("period")
        Glide.with(this)
                .load(getImageUrl)
                .into(imageviewFarmImage)
        textViewDate.text = intent.getStringExtra("scheDate")
        textViewTitle.text = intent.getStringExtra("nhName")
        textViewPrice.text = intent.getIntExtra("nhPrice", 0).toString()
        textViewAddr.text = intent.getStringExtra("nhAddr")

        buttonConfirm.setOnClickListener {
            Toast.makeText(applicationContext, "신청완료!", Toast.LENGTH_SHORT).show()
//            finish()
            var applyCall = ApplicationController.instance!!.networkService!!.applyNh(7, 17)
            applyCall.enqueue(object : Callback<applyResponseData> {
                override fun onResponse(call: Call<applyResponseData>?, response: Response<applyResponseData>?) {
                    if (response!!.isSuccessful) {
                        if (response!!.body().message == "Success To Request For Application") {
                            currentPerson = response!!.body().currentPerson
                            maxPerson = response!!.body().maxPerson

                            val intent = Intent(applicationContext, ApplicationConfirmActivity::class.java)
                            intent.putExtra("currentPerson", currentPerson!!)
                            intent.putExtra("maxPerson", maxPerson!!)
                            intent.putExtra("userName", userDataList!![0].name)
                            intent.putExtra("nhAddr", textViewAddr.text.toString())
                            intent.putExtra("nhName", textViewTitle.text.toString())
                            intent.putExtra("nhPrice", textViewPrice.text.toString())
                            intent.putExtra("period", period)
                            intent.putExtra("start_date", textViewDate.text.toString())

                            startActivity(intent)
                        } else if(response.body().message == "Duplicate to Request"){
                            Toast.makeText(applicationContext, "시간 중복 불가!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<applyResponseData>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "Please Check Network", Toast.LENGTH_SHORT).show()

                }

            })
        }


    }
}
