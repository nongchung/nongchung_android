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
        season1.add("winter")
        season1.add("Inside Games Thrones")
        season1.add("Inside Games Thrones")

        val season2 : MutableList<String> = ArrayList()
        season2.add("winter")
        season2.add("Inside Games Thrones")
        season2.add("Inside Games Thrones")




        header.add("Season:1")
        header.add("Season:2")

        body.add(season1)
        body.add(season2)

        v.expandedView.setAdapter(ExpandAdapter(this.activity!!,expendedView,header,body))

        return  v
    }
}