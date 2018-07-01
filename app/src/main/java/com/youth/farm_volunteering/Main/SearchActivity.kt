package com.youth.farm_volunteering.Main

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

    activity_top_search_icon.setColorFilter(0xFFD1FF, PorterDuff.Mode.MULTIPLY)
    }
}
