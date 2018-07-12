package com.youth.farm_volunteering.MyPage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.toolbar
import com.youth.farm_volunteering.data.NickNameResponseData
import com.youth.farm_volunteering.data.PasswordResourceData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {
    var toolbar : Toolbar? = null
    var password : String? = null
    var newpassword : String? = null
    lateinit var editText: EditText
    lateinit var neweditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        toolbar = findViewById(R.id.toolbarNickChange)
        setSupportActionBar(toolbar)

        editText = findViewById(R.id.editText7)
        neweditText = findViewById(R.id.editText11)


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater = getMenuInflater()
        menuInflater!!.inflate(R.menu.menu_passwordchange, menu)

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_password->{
                password = editText.text.toString()
                newpassword = neweditText.text.toString()


                var passwordCall = ApplicationController.instance!!.networkService!!.password(password!!,newpassword!!)
                passwordCall.enqueue(object : Callback<PasswordResourceData> {
                    override fun onFailure(call: Call<PasswordResourceData>, t: Throwable?) {
                        Toast.makeText(applicationContext, "home request fail", Toast.LENGTH_SHORT).show()

                        //Log.e("abc",t.toString())
                    }

                    override fun onResponse(call: Call<PasswordResourceData>, response: Response<PasswordResourceData>) {

                        when (response.code()) {
                            200 -> {
                                Toast.makeText(this@ChangePasswordActivity, response.body().message, Toast.LENGTH_SHORT).show() // 메시지생성
                            }

                            else ->
                                Toast.makeText(this@ChangePasswordActivity, response.body().message, Toast.LENGTH_SHORT).show()
                        }
                    }
                })

            }
        }

        return super.onOptionsItemSelected(item)
    }
}
