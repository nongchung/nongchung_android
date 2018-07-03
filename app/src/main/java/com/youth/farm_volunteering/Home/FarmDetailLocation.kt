package com.youth.farm_volunteering.Home

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.youth.farm_volunteering.R

class FarmDetailLocation : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.activity_maps, container, false)      //일단 지도만 여기 생성하게 만듬
        //activity!!.supportFragmentManager.beginTransaction().add()
        return v
    }

    override fun onResume() {
        super.onResume()
        var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment;
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}