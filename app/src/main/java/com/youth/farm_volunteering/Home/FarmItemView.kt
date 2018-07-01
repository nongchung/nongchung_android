package com.youth.farm_volunteering

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class FarmItemView(context: Context?) : ConstraintLayout(context) {

    var farmPicture : ImageView? = null
    var farmBookmark : ImageView? = null
    var farmLocation : TextView? = null
    var farmPrice : TextView? = null
    var farmDays : TextView? = null
    var farmName : TextView? = null
    var farmRating : RatingBar? = null
    var farmNumPeople : TextView? = null

    init {
        init(context)
    }
    constructor(context: Context?, attrs: AttributeSet?): this(context){
        init(context)
    }

    fun init(context: Context?){
        val inflater : LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.item_farm, this, true)

        farmPicture = findViewById(R.id.item_farmPicture)
        farmBookmark = findViewById(R.id.item_farmBookmark)
        farmLocation = findViewById(R.id.item_farmLocation)
        farmPrice = findViewById(R.id.item_farmPrice)
        farmDays = findViewById(R.id.item_farmDays)
        farmName = findViewById(R.id.item_farmName)
        farmRating = findViewById(R.id.item_farmRating)
        farmNumPeople = findViewById(R.id.item_farmNumPeople)
    }


}