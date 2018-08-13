package com.youth.farm_volunteering.mypage


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.NickNameResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.text.InputFilter
import android.view.Menu
import android.view.MenuItem


class ChangeNicknameActivity : AppCompatActivity() {

    var changeNick: String? = null
    lateinit var editText: EditText
    lateinit var result: TextView
    var toolbar: Toolbar? = null
    private val m_nMaxLengthOfDeviceName = 20
    var changedNickData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_nickname)

        toolbar = findViewById(R.id.toolbarNickChange)

        setSupportActionBar(toolbar)


        //Log.d("aaa",detailnongwalCall.toString())

        editText = findViewById(R.id.nickname_change_text)
        result = findViewById(R.id.textView55)
        result.maxLines = 20


        //글자수 제한
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(m_nMaxLengthOfDeviceName))
                result.text = count.toString() + "/20"
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = getMenuInflater()
        menuInflater!!.inflate(R.menu.menu_nickchange, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_apply -> {
                changeNick = editText.text.toString()

                var nicknameCall = ApplicationController.instance!!.networkService!!.nickname(changeNick!!)
                nicknameCall.enqueue(object : Callback<NickNameResponseData> {
                    override fun onFailure(call: Call<NickNameResponseData>, t: Throwable?) {
                        Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()
                        //Log.e("abc",t.toString())
                    }


                    override fun onResponse(call: Call<NickNameResponseData>, response: Response<NickNameResponseData>) {



                        when (response.code()) {
                                    200 -> {
                                        intent.putExtra(changeNick, editText.text.toString())
                                        changedNickData = response.body().data // 닉네임을 서버에 전달 null값생성...
                                        Toast.makeText(this@ChangeNicknameActivity, response.body().message, Toast.LENGTH_SHORT).show() // 메시지생성
                                    }
                                    else ->

                                Toast.makeText(this@ChangeNicknameActivity, changeNick, Toast.LENGTH_SHORT).show()
                        // 값이 왜 null이 뜰까요... 대체!!!!!!
                        }

                    }
                })

            }
        }

        return super.onOptionsItemSelected(item)
    }
}
