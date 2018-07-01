package com.youth.farm_volunteering

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class FarmGridAdapter(var dataList : ArrayList<FarmGridData>) : BaseAdapter() {

    override fun getCount(): Int = dataList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return dataList.get(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var farmItemView = FarmItemView(parent!!.context)
        var farmGridData : FarmGridData? = null

        farmGridData = dataList.get(position)

        farmItemView.farmPicture!!.setImageResource(farmGridData.farmPicture)
        farmItemView.farmBookmark!!.setImageResource(farmGridData.farmBookmark)
        farmItemView.farmPrice!!.setText(farmGridData.farmPrice)
        farmItemView.farmNumPeople!!.setText(farmGridData.farmNumPeople)
        farmItemView.farmDays!!.setText(farmGridData.farmDays)
        farmItemView.farmLocation!!.setText(farmGridData.farmLocation)
        farmItemView.farmName!!.setText(farmGridData.farmName)
//        farmItemView.farmRating!!

        return farmItemView
    }


//    var onItemClick : View.OnClickListener? = null
//
//    override fun getItemCount(): Int = dataList.size
//
//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FarmGridViewHolder {
//        val mainView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_farm, parent, false)
//        mainView.setOnClickListener(onItemClick)
//        return FarmGridViewHolder(mainView)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    fun setOnItemClickListener(l:View.OnClickListener){
//        onItemClick = l
//    }
}