package com.youth.farm_volunteering.MyPage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.fragment_mypage_nickname.*
import kotlinx.android.synthetic.main.fragment_mypage_nickname.view.*

class NicknameFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_mypage_nickname, container, false)

        //변경 버튼
        v.nickname_change_btn.setOnClickListener {

        }

        return v
    }

}