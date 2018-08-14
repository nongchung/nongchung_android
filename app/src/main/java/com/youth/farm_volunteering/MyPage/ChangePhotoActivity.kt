package com.youth.farm_volunteering.MyPage

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telecom.Call
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.api.Response
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.nickname_change_text
import com.youth.farm_volunteering.R.id.textView56
import com.youth.farm_volunteering.data.MyPhoto
import kotlinx.android.synthetic.main.activity_start.*

class ChangePhotoActivity : AppCompatActivity() {

    private var REQ_CODE_SELECT_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_photo)

//        val photoPickerIntent = Intent(Intent.ACTION_PICK)
//        photoPickerIntent.type = "image/png"
//        startActivityForResult(photoPickerIntent, REQ_CODE_SELECT_IMAGE)
//        finish()

    }

//                override fun onResponse(call: Call<MyPhoto>, response: Response<MyPhoto>) {
//                when (response.code()) {
//                    200 -> {
////                                intent.putExtra(changeNick, editText.text.toString())
//                        changedNickData = response.body().data // 닉네임을 서버에 전달 null값생성...
//                        if (textView56.visibility == View.VISIBLE) {
//                            Toast.makeText(this@ChangeNicknameActivity, "닉네임을 다시 확인해주세요", Toast.LENGTH_SHORT).show()
//
//                        } else {
//                            //BUNDLE_KEY_NICKNAME
//                            intent.putExtra(changedNickData, nickname_change_text.text.toString())
//                            Toast.makeText(this@ChangeNicknameActivity, response.body().message, Toast.LENGTH_SHORT).show() // 메시지생성
//                            setResult(RESULT_REQUEST_NICKNAME, intent)
//                            finish()
//                        }
//
//                    }
}