package com.youth.farm_volunteering.Expanded

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.expandedView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragement_expanded.*
import kotlinx.android.synthetic.main.fragement_expanded.view.*

class ExpandFragment : Fragment(){

    val header : MutableList<String> = ArrayList()
    val body : MutableList<MutableList<String>> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragement_expanded, container, false)

        var expendedView : ExpandableListView ? = null

        expendedView = v.findViewById(R.id.expandedView)

        val season1 : MutableList<String> = ArrayList()
        season1.add("안됩니다. 서리하면 잡혀가요.")

        val season2 : MutableList<String> = ArrayList()
        season2.add("맛있는 새참이 기다리고 있습니다.\n어서 농활 신청하러 와주세요~! ")

        val season3 : MutableList<String> = ArrayList()
        season3.add("안됩니다. 개별 신청이 원칙입니다. ㅠㅠ! ")

        val season4 : MutableList<String> = ArrayList()
        season4.add("결제가 되지 않을시\n(농활담당자) 010 6577 9381이거나 카카오 친구 으로 연락 주세요")

        val season5 : MutableList<String> = ArrayList()
        season5.add("1. 출발 3일전까지 환불하셔야 100% 환불이 가능합니다.\n2. 출발 2일전과 1일 2일 전은 40%만 환불 가능합니다.\n3. 당일최소는 환불 불가능합니다.\n\n*더 자세한 문의를 원한다면 \n010 0000  (농활청춘 담당자)로 연락해주세요!")

        val season6 : MutableList<String> = ArrayList()
        season6.add("강수량에 따라 취소될수도 있습니다. \n취소되면 연락을 드리고 환불처리 해드립니다. \n환불은 3일이내 처리됩니다.")


        header.add("서리해도 되나요?")
        header.add("새참도 제공해주시나요?")
        header.add("친구랑 같이 가고 싶은데 한번에 두명 신청 안되나요?")
        header.add("결제가 되지 않아요.")
        header.add("환불은 어떻게 되나요?")
        header.add("우천시에는 농활 어떻게 하나요?")

        body.add(season1)
        body.add(season2)
        body.add(season3)
        body.add(season4)
        body.add(season5)
        body.add(season6)

        v.expandedView.setAdapter(ExpandAdapter(this.activity!!,expendedView,header,body))

        return  v
    }
}