package com.asksira.loopingviewpagerdemo

//import com.asksira.loopingviewpager.LoopingPagerAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.home.LoopingPagerAdapter
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.AdData

class DemoInfiniteAdapter(context: Context, itemList: List<AdData>, isInfinite: Boolean) : LoopingPagerAdapter<Int>(context, itemList, isInfinite) {


//    override fun getItemViewType(listPosition: Int): Int {
//        return VIEW_TYPE_NORMAL
//    }

    override fun inflateView(viewType: Int, listPosition: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.item_pager, null)
//        else LayoutInflater.from(context).inflate(R.layout.item_special, null)
    }

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        Glide.with(this.context)
                .load(itemList[listPosition].img)
                .into(convertView.findViewById(R.id.item_slideImage))
//        convertView.findViewById<ImageView>(R.id.item_slideImage).setBackgroundResource(itemList[listPosition].img)
        convertView.findViewById<TextView>(R.id.textviewAdCurCnt).text = itemList[listPosition].idx.toString() + " / "
        convertView.findViewById<TextView>(R.id.textviewAdSize).text = itemList.size.toString()
    }


    companion object {

        private val VIEW_TYPE_NORMAL = 100
        private val VIEW_TYPE_SPECIAL = 101
    }
}
