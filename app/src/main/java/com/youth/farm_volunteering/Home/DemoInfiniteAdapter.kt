package com.asksira.loopingviewpagerdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.youth.farm_volunteering.R
import java.util.*

class DemoInfiniteAdapter(context: Context, itemList: ArrayList<Int>, isInfinite: Boolean) : LoopingPagerAdapter<Int>(context, itemList, isInfinite) {

    override fun getItemViewType(listPosition: Int): Int {
        return VIEW_TYPE_NORMAL
    }

    override fun inflateView(viewType: Int, listPosition: Int): View {
        return if (viewType == VIEW_TYPE_NORMAL) LayoutInflater.from(context).inflate(R.layout.item_pager, null)
        else LayoutInflater.from(context).inflate(R.layout.item_special, null)
    }

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        convertView.findViewById<ImageView>(R.id.item_slideImage).setImageResource(itemList[listPosition])
    }


    companion object {

        private val VIEW_TYPE_NORMAL = 100
        private val VIEW_TYPE_SPECIAL = 101
    }
}
