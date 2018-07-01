package com.youth.farm_volunteering

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.youth.farm_volunteering.Home.FarmDetailLocation
import com.youth.farm_volunteering.Home.FarmDetailReview
import com.youth.farm_volunteering.Home.FarmDetailintroduce

import android.widget.Toast
import com.youth.farm_volunteering.R.id.*

import kotlinx.android.synthetic.main.activity_farm_detail.*

class FarmDetailActivity : AppCompatActivity(), View.OnClickListener {

    var toolbar : android.support.v7.widget.Toolbar? = null

    override fun onClick(v: View?) {
        when(v){
            farm_introduce -> {
                clearSelected()
                farm_introduce.isSelected = true
                replaceFragment(FarmDetailintroduce())
            }
            farm_location -> {
                clearSelected()
                farm_location.isSelected = true
                replaceFragment(FarmDetailLocation())
            }
            farm_review -> {
                clearSelected()
                farm_review.isSelected = true
                replaceFragment(FarmDetailReview())
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farm_detail)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar!!.setTitleTextColor(0xFF000000.toInt())
        toolbar!!.title = " "

        toolbarImage.setImageResource(intent.getIntExtra("farm_img",0))
        detail_location_tv.setText(intent.getStringExtra("farm_location"))
        detail_name_tv.setText(intent.getStringExtra("farm_name"))
        detail_price_tv.setText(intent.getStringExtra("farm_price"))
        detail_days_tv.setText(intent.getStringExtra("farm_days"))


        addFragment(FarmDetailintroduce())
        farm_introduce.isSelected = true
        farm_introduce.setOnClickListener(this)
        farm_location.setOnClickListener(this)
        farm_review.setOnClickListener(this)
        detail_apply_btn.setOnClickListener{
            Toast.makeText(applicationContext, "신청버튼 누름", Toast.LENGTH_SHORT).show()
            if(detail_apply_rv.visibility == View.GONE){
            detail_apply_rv.visibility = View.VISIBLE}
            else if(detail_apply_rv.visibility == View.VISIBLE){
            detail_apply_rv.visibility = View.GONE}

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var menuInflater = getMenuInflater()
        menuInflater!!.inflate(R.menu.menu_farmdetail, menu)

        var bookmark : Drawable = menu!!.getItem(0).icon
        bookmark.setColorFilter(0xFFFFFFFF.toInt(), PorterDuff.Mode.MULTIPLY)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.menu_detail_bookmark -> {
//                val intent = Intent(applicationContext, FarmDetailActivity::class.java)
//                startActivity(intent)
            }
        }

        return false
    }


    fun clickFloat(){

    }

    fun addFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.detail_frame, fragment)
        transaction.commit()
    }

    fun replaceFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.detail_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun clearSelected(){
        farm_introduce.isSelected = false
        farm_location.isSelected = false
        farm_review.isSelected = false
    }



}
