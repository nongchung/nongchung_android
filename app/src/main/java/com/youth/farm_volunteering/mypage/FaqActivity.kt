package com.youth.farm_volunteering.mypage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_faq.*

class FaqActivity : AppCompatActivity() {

    var noticeAdapter : NoticeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val header : MutableList<String> = ArrayList()
        val body : MutableList<MutableList<String>> = ArrayList()

        var faqexpandedView : ExpandableListView? = null

        faqexpandedView = findViewById(R.id.faq_expandedView)

        val season1 : MutableList<String> = ArrayList()
        season1.add("더보기> 알림> 설정\n푸쉬알림설정에서 알림을 on상태로 해주세요.")

        val season2 : MutableList<String> = ArrayList()
        season2.add("재판의 전심절차로서 행정심판을 할 수 있다.\n행정심판의 절차는 법률로 정하되, 사법절차가 준용되어야 한다.\n\n국가는 전통문화의 계승·발전과 민족문화의 창달에 노력하여야 한다.")

        val season3 : MutableList<String> = ArrayList()
        season3.add("국가는 농수산물의 수급균형과 유통구조의 개선에 노력하여 가격안정을 도모함으로써 농·어민의 이익을 보호한다.\n\n헌법재판소 재판관은 탄핵 또는 금고 이상의 형의 선고에 의하지 아니하고는 파면되지 아니한다. ")


        header.add("결제가 진행되지 않으면 어떻게 해야하나요?")
        header.add("푸쉬알람이 오지 않으면 어떻게 해야하나요?")
        header.add("이 활동 어디서 하는 건가요??")


        body.add(season1)
        body.add(season2)
        body.add(season3)

        faq_expandedView.setAdapter(NoticeAdapter(this!!,faqexpandedView,header,body))

    }
}
