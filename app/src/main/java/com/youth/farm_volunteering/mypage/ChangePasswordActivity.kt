package com.youth.farm_volunteering.mypage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.*
import com.youth.farm_volunteering.data.NickNameResponseData
import com.youth.farm_volunteering.data.PasswordResourceData
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_signup1.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {
    var toolbar : Toolbar? = null
    var password : String? = null
    var newpassword : String? = null
    lateinit var editText: EditText
    lateinit var neweditText: EditText
    var passwordChangeData: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        toolbar = findViewById(R.id.toolbarpassword)
        setSupportActionBar(toolbar)

        editText = findViewById(R.id.editText7)
        neweditText = findViewById(R.id.editText11)

        textView88.visibility = View.INVISIBLE
        imageView13.visibility = View.INVISIBLE

//        editText11.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//                if(editText7.text.toString() != p0.toString()){
//                    textView88.visibility = View.VISIBLE
//                    imageView13.visibility = View.VISIBLE
//                }
//                else if(editText7.text.toString() == p0.toString()){
//                    textView88.visibility = View.INVISIBLE
//                    imageView13.visibility = View.INVISIBLE
//                }
//            }
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//        })

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = getMenuInflater()
        menuInflater!!.inflate(R.menu.menu_passwordchange, menu)

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_password->{
                password = editText.text.toString() //비밀번호 친거
                newpassword = neweditText.text.toString() //새로운 비밀번호 친거


                var passwordCall = ApplicationController.instance!!.networkService!!.password(password!!,newpassword!!)
                passwordCall.enqueue(object : Callback<PasswordResourceData> {
                        override fun onFailure(call: Call<PasswordResourceData>, t: Throwable?) {
                            Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()

                            //Log.e("abc",t.toString())
                        }

                    override fun onResponse(call: Call<PasswordResourceData>, response: Response<PasswordResourceData>) {

                        when (response.code()) {
                            200 -> {

                                    intent.putExtra(passwordChangeData, editText11.text.toString())
                                    Toast.makeText(this@ChangePasswordActivity, response.body().message, Toast.LENGTH_SHORT).show() // 메시지생성
                                    finish()
                            }
                            400 -> {
                                if (editText7.text.toString() == "") {
                                    Toast.makeText(this@ChangePasswordActivity, "비밀번호를 다시 확인해주세요", Toast.LENGTH_SHORT).show()

                                } else if (editText11.text.toString() == "") {
                                    Toast.makeText(this@ChangePasswordActivity, "새 비밀번호를 다시 확인해주세요", Toast.LENGTH_SHORT).show()
                                }
                            }
                            500 ->
                                Toast.makeText(this@ChangePasswordActivity, response.body().message, Toast.LENGTH_SHORT).show()
                        }
                    }
                })

            }
        }

        return super.onOptionsItemSelected(item)
    }
}
