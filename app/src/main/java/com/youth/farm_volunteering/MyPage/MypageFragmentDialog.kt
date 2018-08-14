package com.youth.farm_volunteering.MyPage

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_change_nickname.*

class MypageFragmentDialog : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_change_nickname)
        //엑티비티는 아무거나씀
    }
    fun showDialog(view: View){
        var builder : AlertDialog.Builder = AlertDialog.Builder(this)
        var inflater : LayoutInflater = layoutInflater
        var view : View = inflater.inflate(R.layout.test,null)
        builder.setView(view)
        builder.setNegativeButton("No",object  : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Toast.makeText(this@MypageFragmentDialog,"로그인안함",Toast.LENGTH_LONG).show()
                dialog!!.dismiss()
            }
        })
        builder.setPositiveButton("Yes",object  : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Toast.makeText(this@MypageFragmentDialog,"로그인안함",Toast.LENGTH_LONG).show()
                dialog!!.dismiss()
            }
        })
        var dialog: Dialog = builder.create()
        dialog.show()
    }
}