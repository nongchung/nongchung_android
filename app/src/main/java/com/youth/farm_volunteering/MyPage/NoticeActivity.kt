package com.youth.farm_volunteering.MyPage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import com.youth.farm_volunteering.Expanded.ExpandAdapter
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_notice.*

class NoticeActivity : AppCompatActivity() {

    var noticeAdapter : NoticeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        val header : MutableList<String> = ArrayList()
        val body : MutableList<MutableList<String>> = ArrayList()

        var noticeexpandedView : ExpandableListView? = null

        noticeexpandedView = findViewById(R.id.notice_expandedView)

        val season1 : MutableList<String> = ArrayList()
        season1.add("우리 농촌 활동 함께해요 특집이 게시되었습니다.\n특집은 농활참여자들이 학생일 경우 10%할인 됩니다.\n많은 관심 부탁드립니다.\n\n*특집 농활*\n1.우도환 완전 좋아좋아 짱이야 농장\n2서강준 짱멋져 농장\n3.박서준이 최고지 농장")

        val season2 : MutableList<String> = ArrayList()
        season2.add("재판의 전심절차로서 행정심판을 할 수 있다.\n행정심판의 절차는 법률로 정하되, 사법절차가 준용되어야 한다.\n\n국가는 전통문화의 계승·발전과 민족문화의 창달에 노력하여야 한다.")

        val season3 : MutableList<String> = ArrayList()
        season3.add("국가는 농수산물의 수급균형과 유통구조의 개선에 노력하여 가격안정을 도모함으로써 농·어민의 이익을 보호한다.\n\n헌법재판소 재판관은 탄핵 또는 금고 이상의 형의 선고에 의하지 아니하고는 파면되지 아니한다. ")


        header.add("업데이트8.8")
        header.add("특집: 제주도 감귤농장")
        header.add("농촌활동 신청시 취소 공지사항")


        body.add(season1)
        body.add(season2)
        body.add(season3)

        notice_expandedView.setAdapter(NoticeAdapter(this!!,noticeexpandedView,header,body))

    }
}
