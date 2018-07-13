package com.youth.farm_volunteering.Home
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.Home.FarmProfile.FarmProfileActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.DetailSchData
import com.youth.farm_volunteering.data.FarmInfoData
import com.youth.farm_volunteering.data.FriendInfoData
import com.youth.farm_volunteering.data.NhInfoData
import kotlinx.android.synthetic.main.fragment_farm_introduce.view.*

class FarmIntroFragment : Fragment() {

    lateinit var friendinfoAdapter: FriendInfoAdapter
    lateinit var scheduleAdapter: ScheduleAdapter

    var DetailNonghwalList: NhInfoData? = null
    var DetailFriendInfoList: List<FriendInfoData>? = null
    var DetailFarmInfoList: FarmInfoData? = null
    var DetailScheduleList: List<DetailSchData>? = null
    var nhIdx : Int? = null

    private var introduceImage_linearLayoutManager: LinearLayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_farm_introduce, container, false)
//        activity!!.supportFragmentManager.beginTransaction().add()

        DetailNonghwalList = arguments.getParcelable("nhInfo")
        DetailFriendInfoList = arguments.getParcelableArrayList("friendsInfo")
        DetailFarmInfoList = arguments.getParcelable("farmerInfo")

        v.detail_introduce_addr.text = DetailNonghwalList!!.addr
        v.detail_introduce_name.text = DetailNonghwalList!!.name
//                setText(DetailNonghwalList!!.star.toString())
        v.detail_introduce_star.rating = DetailNonghwalList!!.star!!.toFloat() //rating에 서버에서 float값 받아와서 생성
        v.detail_introduce_description.text = DetailNonghwalList!!.description
        v.detail_introduce_price.text = DetailNonghwalList!!.price.toString()
        v.detail_introduce_period.text = DetailNonghwalList!!.period.toString()

        friendinfoAdapter = FriendInfoAdapter(DetailFriendInfoList!!)

        v.friendinfoView_rv.layoutManager = LinearLayoutManager(activity.applicationContext)
        v.friendinfoView_rv.adapter = friendinfoAdapter

        introduceImage_linearLayoutManager = LinearLayoutManager(activity.applicationContext, LinearLayoutManager.HORIZONTAL, false)

        v.friendinfoView_rv!!.layoutManager = introduceImage_linearLayoutManager

        v.farminfo_name.text = DetailFarmInfoList!!.name
        v.farminfo_comment.text = DetailFarmInfoList!!.comment
        Glide.with(activity.applicationContext)
                .load(DetailFarmInfoList!!.img)
                .into(v.farminfo_image)

//
//                Glide.with(holder!!.itemView.context)
//                        .load(dataList[position]) //String 줘서 이렇게??
//                        .into(holder.FarmBoxReviewImg)

        // 스캐줄부분은 잠시 생략...
//        scheduleAdapter = ScheduleAdapter(DetailScheduleList!!)
//
//        v.scheduleView_rv.layoutManager = LinearLayoutManager(activity.applicationContext)
//        v.scheduleView_rv.adapter = scheduleAdapter

        //size가 6이상일때는 +이미지가 표시되게 함
//            val intent = Intent(activity.applicationContext, FriendInfoAllActivity::class.java)
//            startActivity(intent)

        v.detail_profile_watch_btn.setOnClickListener(View.OnClickListener  {
            var v = Intent(this.context,FarmProfileActivity::class.java)
            startActivity(v)
        })

        return v


    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        IntroduceImageView_rv.layoutManager = LinearLayoutManager(context)
////
//        introduceImage_linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
//
//        IntroduceImageView_rv!!.setLayoutManager(introduceImage_linearLayoutManager)}


}


