package com.youth.farm_volunteering

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youth.farm_volunteering.data.NonghwalData
import kotlinx.android.synthetic.main.fragment_showall.*
import java.util.ArrayList

class ShowAllFragment : Fragment() {
    lateinit var farmAdapter: FarmAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_showall, container, false)

        var farmList : ArrayList<NonghwalData>? = null


        farmList = ArrayList()

        farmAdapter = FarmAdapter(farmList!!)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_showall_rv.layoutManager = LinearLayoutManager(context)

        fragment_showall_rv.adapter = farmAdapter

    }
}
