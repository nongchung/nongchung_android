package com.youth.farm_volunteering.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.FarmDetailActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.AllStData
import com.youth.farm_volunteering.data.UserData
import com.youth.farm_volunteering.login.LoginToken
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
        var getStData : AllStData? = null
        var currentPerson: Int? = null
        var maxPerson: Int? = null
        var period: String? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        userDataList = intent.getParcelableArrayListExtra("userData")

        getNhIdx = intent.getIntExtra("nhIdx", 0)
        getScheIdx = intent.getIntExtra("scheIdx", 0)
        getStData = intent.getParcelableExtra("selectedStData")


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
        textViewPrice.text = intent.getIntExtra("nhPrice", 0).toString()+ "원"
        textViewAddr.text = intent.getStringExtra("nhAddr")

        buttonConfirm.setOnClickListener {
            if(LoginToken.token != null) {
//            finish()
                val applyCall = ApplicationController.instance!!.networkService!!.applyNh(getNhIdx!! , getScheIdx!!)
                applyCall.enqueue(object : Callback<applyResponseData> {
                    override fun onResponse(call: Call<applyResponseData>?, response: Response<applyResponseData>?) {
                        if(response!!.code() ==400){
                            Toast.makeText(applicationContext, "시간이 중복되었습니다!\n 내 활동목록을 확인해주세요!", Toast.LENGTH_SHORT).show()
                        }
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
                                intent.putExtra("selectedStData",getStData)
                                startActivityForResult(intent, FarmDetailActivity.applyReqCode)
                            } else if (response.body().message == "Duplicate to Time") {
                                Toast.makeText(applicationContext, "시간 중복 불가!", Toast.LENGTH_SHORT).show()
                            } else if (response.body().message == "No token") {
                                Toast.makeText(applicationContext, "토큰 에러!", Toast.LENGTH_SHORT).show()
                            } else if (response.body().message == "Null Value") {
                                Toast.makeText(applicationContext, "값이 비어있습니다!", Toast.LENGTH_SHORT).show()
                            } else if (response.body().message == "Invalid schIdx") {
                                Toast.makeText(applicationContext, "신청 불가능한 스케줄 입니다!", Toast.LENGTH_SHORT).show()
                            } else if (response.body().message == "Fail To Request For Application, No Available Person Number") {
                                Toast.makeText(applicationContext, "여석이 없습니다!", Toast.LENGTH_SHORT).show()
                            } else if (response.body().message == "Invalid nhIdx and schIdx") {
                                Toast.makeText(applicationContext, "해당하는 농활이나 스케줄이 없습니다!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(applicationContext, "에바참치", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<applyResponseData>?, t: Throwable?) {
                        Toast.makeText(applicationContext, "Please Check Network", Toast.LENGTH_SHORT).show()

                    }

                })
            }else{
                Toast.makeText(applicationContext, "로그인이 필요합니다!", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==FarmDetailActivity.applyReqCode){
            finish()
        }
    }
}
