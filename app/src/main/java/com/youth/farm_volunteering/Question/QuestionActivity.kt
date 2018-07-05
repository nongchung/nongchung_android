package com.youth.farm_volunteering.Question

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R

class QuestionActivity : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.item_question, container, false)      //일단 지도만 여기 생성하게 만듬
        //activity!!.supportFragmentManager.beginTransaction().add()
        return v
    }
}