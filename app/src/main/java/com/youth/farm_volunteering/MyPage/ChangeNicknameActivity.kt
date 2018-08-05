package com.youth.farm_volunteering.MyPage


import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_change_nickname.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.file.WatchEvent
import android.text.InputFilter
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.youth.farm_volunteering.R.id.*
import com.youth.farm_volunteering.SignUp.BUNDLE_KEY_EMAIL
import com.youth.farm_volunteering.SignUp.BUNDLE_KEY_NICKNAME
import com.youth.farm_volunteering.SignUp.BUNDLE_KEY_PASSWORD
import com.youth.farm_volunteering.SignUp.RESULT_REQUEST_LOGIN
import com.youth.farm_volunteering.StartActivity
import com.youth.farm_volunteering.data.DefaultResponseData
import com.youth.farm_volunteering.data.DupResponseData
import com.youth.farm_volunteering.data.NhInfoData
import com.youth.farm_volunteering.login.LoginData
import com.youth.farm_volunteering.login.LoginData.nickname
import com.youth.farm_volunteering.login.LoginToken
import kotlinx.android.synthetic.main.activity_signup2.*

const val RESULT_REQUEST_NICKNAME: Int = 3
class ChangeNicknameActivity : AppCompatActivity() {

    var changeNick: String? = null
    var newchangeNick: String? = null
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


        textView56.visibility = View.INVISIBLE
        imageView9.visibility = View.INVISIBLE
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

        nickname_change_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val nick = p0.toString()
                if(nick == ""){}
                else{
                    var nick_dup = ApplicationController.instance!!.networkService!!.nickDup(nick)
                    nick_dup.enqueue(object : Callback<DupResponseData> {
                        override fun onFailure(call: Call<DupResponseData>?, t: Throwable?) {
                            Toast.makeText(this@ChangeNicknameActivity, "nick_dup request fail", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<DupResponseData>?, response: Response<DupResponseData>?) {
                            if (response!!.body().message == "available") {
                                textView56.visibility = View.INVISIBLE
                                imageView9.visibility = View.INVISIBLE
                            } else if (response!!.body().message == "duplication") {
                                textView56.visibility = View.VISIBLE
                                imageView9.visibility = View.VISIBLE
                            }
                        }
                    })}
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = getMenuInflater()
        menuInflater!!.inflate(R.menu.menu_nickchange, menu)


        return super.onCreateOptionsMenu(menu)
    }

//    private fun requestRegistration(email: String, password: String, nickname: String, name: String, sex: Int, handphone: String, birth: String) {
//
//        var mypageCall = ApplicationController.instance!!.networkService!!.registration(email, password, nickname, name, sex, handphone, birth)
//        mypageCall.enqueue(object : Callback<DefaultResponseData> {
//            override fun onFailure(call: Call<DefaultResponseData>, t: Throwable?) {
//                Toast.makeText(this@SignupActivity1, "signup request fail", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(call: Call<DefaultResponseData>, response: Response<DefaultResponseData>) {
//                Toast.makeText(this@SignupActivity1, name + "님, 농활청춘에 오신 것을 환영합니다!", Toast.LENGTH_SHORT).show()
//                intent.putExtra(BUNDLE_KEY_EMAIL, email)
//                intent.putExtra(BUNDLE_KEY_PASSWORD, password)
//                setResult(RESULT_REQUEST_LOGIN, intent)
//                finish()
//
//            }
//        })
//    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_apply -> {
                changeNick = editText.text.toString()
                newchangeNick = editText.text.toString()



                var nicknameCall = ApplicationController.instance!!.networkService!!.nickname(changeNick!!,newchangeNick!!)
                nicknameCall.enqueue(object : Callback<NickNameResponseData> {
                    override fun onFailure(call: Call<NickNameResponseData>, t: Throwable?) {
                        Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()
                        //Log.e("abc",t.toString())
                    }


                    override fun onResponse(call: Call<NickNameResponseData>, response: Response<NickNameResponseData>) {
                        when (response.code()) {
                            200 -> {
//                                intent.putExtra(changeNick, editText.text.toString())
                                changedNickData = response.body().data // 닉네임을 서버에 전달 null값생성...
                                if (textView56.visibility == View.VISIBLE) {
                                    Toast.makeText(this@ChangeNicknameActivity, "닉네임을 다시 확인해주세요", Toast.LENGTH_SHORT).show()

                                } else {
                                    //BUNDLE_KEY_NICKNAME
                                    intent.putExtra(changedNickData, nickname_change_text.text.toString())
                                    Toast.makeText(this@ChangeNicknameActivity, response.body().message, Toast.LENGTH_SHORT).show() // 메시지생성
                                    setResult(RESULT_REQUEST_NICKNAME, intent)
                                    finish()
                                }

                            }
                            400 -> {
//                                var sharedPreference = getSharedPreferences(LoginToken.PREF_KEY, Context.MODE_PRIVATE); // 잘모르고씀
//                                var editor = sharedPreference.edit();
//                                editor.putString("uNickname", response.body().data!!)
//                                LoginToken.token = response.body().data
//                                LoginData.nickname = response.body().data!![0].toString()
                                Toast.makeText(this@ChangeNicknameActivity, response.body().message + "실패", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            500 -> {
//                                var sharedPreference = getSharedPreferences(LoginToken.PREF_KEY, Context.MODE_PRIVATE); // 잘모르고씀
//                                var editor = sharedPreference.edit();
//                                editor.putString("uNickname", response.body().data!!)
//                                LoginToken.token = response.body().data
//                                LoginData.nickname = response.body().data!![0].toString()
                                Toast.makeText(this@ChangeNicknameActivity, response.body().message + "실패", Toast.LENGTH_SHORT).show()
                                finish()
                            }

                        // 값이 왜 null이 뜰까요... 대체!!!!!!
                        }

                    }
                })

            }
        }

        return super.onOptionsItemSelected(item)
    }
}
