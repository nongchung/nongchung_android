//package com.youth.farm_volunteering
//
//
//import android.content.Context
//import android.support.v4.view.PagerAdapter
//import android.support.v4.view.ViewPager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import kotlinx.android.synthetic.main.item_pager.view.*
//import java.util.*
//


//class AdViewPagerAdapter(context: Context) : PagerAdapter(){
//
//    var context : Context = context
//    var layoutInflator : LayoutInflater? = null
//    var slideImages : ArrayList<Int> = arrayListOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4)
//
//
//    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
//        return view == `object`
//    }
//
//    override fun getCount(): Int = slideImages.size
//
//    override fun instantiateItem(container : ViewGroup, position : Int) : Any?{
//        layoutInflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view : View = layoutInflator!!.inflate(R.layout.item_pager, null)
//
//        view.item_slideImage.setImageResource(slideImages[position])
//
//        var vp : ViewPager = container as ViewPager
//        vp.addView(view, 0)
//        return view
//    }
//
//    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
//        var vp : ViewPager = container as ViewPager
//        var view : View = `object` as View
//        vp.removeView(view)
//    }
//}