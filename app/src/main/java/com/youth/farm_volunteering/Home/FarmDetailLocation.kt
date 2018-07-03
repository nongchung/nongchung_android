package com.youth.farm_volunteering.Home

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.youth.farm_volunteering.R

class FarmDetailLocation : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_maps, container, false)      //일단 지도만 여기 생성하게 만듬
        //activity!!.supportFragmentManager.beginTransaction().add()
        return v
    }

    override fun onResume() {
        super.onResume()
        var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment;
        mapFragment.getMapAsync(activity as OnMapReadyCallback)
    }
}