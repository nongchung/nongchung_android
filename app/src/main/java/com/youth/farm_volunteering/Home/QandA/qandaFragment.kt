package com.youth.farm_volunteering.Home.QandA

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_farm_detail_qanda.*
import kotlinx.android.synthetic.main.activity_farm_detail_qanda.view.*
import kotlinx.android.synthetic.main.activity_farm_detail_qandaitem.*
import kotlinx.android.synthetic.main.activity_farm_detail_qandaitem.view.*

class qandaFragment : Fragment() {
    lateinit var qandaItems : ArrayList<qandaItem>
    lateinit var qandaAdepter: qandaAdepter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.activity_farm_detail_qanda, container, false)

        qandaItems = ArrayList()
        qandaItems.add(qandaItem("신청은 어떻게 하나요", "몰랑"))
        qandaItems.add(qandaItem("결제는 어떻게 하나요", "몰랑ㅋㅅㅋ"))

        qandaAdepter = qandaAdepter(qandaItems)
        v.qanda_rv.layoutManager = LinearLayoutManager(this.activity.applicationContext)
        v.qanda_rv.adapter = qandaAdepter

//        v.question_button.setOnClickListener {
//            if (v.answer_text.visibility == View.GONE) {
//                v.answer_text.visibility = View.VISIBLE
//            } else if (v.answer_text.visibility == View.VISIBLE) {
//                    v.answer_text.visibility = View.GONE
//            }
//        }


        return v
    }


}