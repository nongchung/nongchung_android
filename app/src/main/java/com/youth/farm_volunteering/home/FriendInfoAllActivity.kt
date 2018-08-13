package com.youth.farm_volunteering.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.FriendInfoData
import kotlinx.android.synthetic.main.activity_allfriendinfo.*

const val BUNDLEKEY_KEY_FRIENDINFO: String = "BUNDLEKEY_KEY_FRIENDINFO"

class FriendInfoAllActivity : AppCompatActivity() {

    lateinit var friendAllinfoAdapter: FriendInfoAllAdapter
    var FriendInfoAllList: List<FriendInfoData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allfriendinfo)


        FriendInfoAllList = intent.getSerializableExtra(BUNDLEKEY_KEY_FRIENDINFO) as List<FriendInfoData>
        friendAllinfoAdapter = FriendInfoAllAdapter(FriendInfoAllList!!)

        allfriendifo_rv.layoutManager = LinearLayoutManager(applicationContext)
        allfriendifo_rv.adapter = friendAllinfoAdapter
        //모든 참석자 화면을 위한 manager설정


//                friendinfoView_rv.layoutManager = LinearLayoutManager(context)
//                friendinfoView_rv.adapter = friendinfoAdapter

        //applicatinocontext
    }
}