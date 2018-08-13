package com.youth.farm_volunteering.mypage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
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
        season1.add("검색기능\n  -내가 가고싶은 날짜와 지역의 농활을 검색해보세요!\n\n찜하기기능\n  -가고 싶은 농활을 찜해보세요! \n\n후기작성 \n  	-참여한 농활의 후기를 작성하고 \n     다른 사람들의 후기도 확인해보세요!")

        val season2 : MutableList<String> = ArrayList()
        season2.add("\t다양한 특집의 농활에 참여해보세요~\n\n\n1. 힐링 농활 특집\n    힐링이 필요한 사람들을 위한 휴양 농활\n\n" +
                "2. 맛있는 농활 특집\n    100여개의 후기 중 엄선한 새참이 맛있는 농활 \n\n" +
                "3. 그린투어리즘 특집\n    근처 관광지가 많아 관광까지 한꺼번에 즐길 수 있는 농활\n\n" +
                "4. 오직 8월에만!\n    지역 축제를 함께 즐길 수 있는 농활!\n\n" +
                "5. 서울 경기 근교 과일 농장\n    과일 농장!\n\n")

        val season3 : MutableList<String> = ArrayList()
        season3.add("1. 출발 3일전까지 환불하셔야 100% 환불이 가능합니다.\n\n2. 출발 2일전과 1일 2일 전은 40%만 환불 가능합니다.\n\n3. 당일최소는 환불 불가능합니다.")


        header.add("업데이트 1.0, 이 스펙 실화임?")
        header.add("다양한 농활특집!")
        header.add("농활 취소시 환불규정!")


        body.add(season1)
        body.add(season2)
        body.add(season3)

        notice_expandedView.setAdapter(NoticeAdapter(this!!,noticeexpandedView,header,body))

    }
}
