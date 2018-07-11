package com.youth.farm_volunteering.Question

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.Home.ReviewAdapter
import com.youth.farm_volunteering.Home.ReviewData
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.fragment_question.view.*
import java.util.ArrayList

class QuestionFragment : Fragment() {

    lateinit var questionitems: ArrayList<QuestionData>
    lateinit var questionAdapter : QuestionAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.item_question, container, false)      //이건 큐엔에이

        questionitems = ArrayList()
        questionitems.add(QuestionData("수고많습니다."))
        questionitems.add(QuestionData("수고많습니다."))

        questionAdapter = QuestionAdapter(questionitems)
//        v.questionView.layoutManager = LinearLayoutManager(this.activity.applicationContext)
//        v.questionView.adapter = questionAdapter recyclerview 안쓸생각 expandable 쓰겟습니다



        return v



    }
}
