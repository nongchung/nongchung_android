package com.youth.farm_volunteering.area

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.search.SelectAreaAdapter
import kotlinx.android.synthetic.main.activity_selectarea.*
import java.util.HashMap

class AreaSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectarea)
        var map: HashMap<Int, Boolean> = HashMap();
        enumValues<Area>().forEach { area ->
            map.put(area.code, false)
        }

        recyclerview_selectarea_area.adapter = SelectAreaAdapter(map)
        recyclerview_selectarea_area.layoutManager = GridLayoutManager(this, 4)
        button_selectarea_select.setOnClickListener {
            intent.putExtra("area_map", (recyclerview_selectarea_area.adapter as SelectAreaAdapter).items)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}