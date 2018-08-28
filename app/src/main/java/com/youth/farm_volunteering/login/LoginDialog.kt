package com.youth.farm_volunteering.login

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.youth.farm_volunteering.R
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.youth.farm_volunteering.login.LoginActivity
import kotlinx.android.synthetic.main.dialog_application_cancel.*
import kotlinx.android.synthetic.main.dialog_login.*


class LoginDialog : Dialog {

    var LoginOkListener : View.OnClickListener
    var LoginCancelListener : View.OnClickListener
    var SignUpListener : View.OnClickListener

    constructor(context: Context, LoginOkListener : View.OnClickListener, LoginCancelListener: View.OnClickListener, SignUpListener : View.OnClickListener) : super(context){
        this.LoginOkListener = LoginOkListener
        this.LoginCancelListener = LoginCancelListener
        this.SignUpListener = SignUpListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        window!!.attributes = lpWindow

        setContentView(R.layout.dialog_login)

        if((LoginOkListener!= null) and (LoginCancelListener!=null) and (SignUpListener!=null)){
            button_dialog_yes.setOnClickListener(LoginOkListener)
            button_dialog_no.setOnClickListener(LoginCancelListener)
            textview_dialog_signup.setOnClickListener(SignUpListener)
        }

    }
}