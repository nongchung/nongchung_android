package com.youth.farm_volunteering.MyActivity

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.Review.ReviewWriteActivity


class MyactivityAdapter(var mydataList: List<MyActivityData>) : RecyclerView.Adapter<MyactivityViewHolder>() {
    override fun getItemCount(): Int = mydataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyactivityViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_myactivity, parent, false)
        return MyactivityViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: MyactivityViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
                .load(mydataList[position].img)
                .into(holder.img)

        holder.startDate.text = mydataList[position].startDate
        holder.endDate.text = mydataList[position].endDate
        holder.addr.text = mydataList[position].addr
        holder.period.text = mydataList[position].period
        holder.name.text = mydataList[position].name
        holder.person.text = mydataList[position].person.toString()
        holder.price.text = mydataList[position].price.toString()
        holder.currentPerson.text = mydataList[position].currentPerson.toString()
        holder.personLimit.text = mydataList[position].personLimit.toString()
        holder.changebar.max = mydataList[position].personLimit!!
        holder.changebar.setProgress(mydataList[position].currentPerson!!)
        holder.textviewWrieteReview.setOnClickListener {
            val intent = Intent(holder.itemView.context, ReviewWriteActivity::class.java)
            intent.putExtra("MyActivityData", mydataList[position])
            holder.itemView.context.startActivity(intent)
        }

        if (mydataList[position].state != null) {
            when (mydataList[position].state) {
                0 -> {
                    holder.changeimg.setImageResource(R.drawable.mymy_ic_ing)
                    holder.changemoney.isSelected = false
                }
                1 -> {
                    holder.changeimg.setImageResource(R.drawable.mymy_ic_ing)
                    holder.changemoney.isSelected = true
                }
                2 -> {
                    holder.changeimg.visibility = View.GONE
                    holder.changemoney.setImageResource(R.drawable.mymy_complete)
                    holder.constraintLayoutApplyIng.visibility = View.GONE
                    holder.textviewWrieteReview.visibility = View.VISIBLE

                }
                3 -> {

                    holder.changeimg.visibility = View.GONE
                    holder.changemoney.setImageResource(R.drawable.mymy_cancel)
                    holder.canceltext.visibility = View.VISIBLE
                    holder.img.setColorFilter(Color.parseColor("#8A000000"))

                }
                4 -> {
                    holder.changemoney.setImageResource(R.drawable.mymy_extention)
                    holder.changeimg.visibility = View.GONE
                }
            }

        }
        if (mydataList[position].personLimit == mydataList[position].currentPerson) {
            holder.changemoney.setImageResource(R.drawable.mymy_extention)
            holder.changeimg.visibility = View.GONE
        }

//        if(dataList[position].isBooked!=null){
//            when(dataList[position].isBooked){
//                0-> holder.imageviewNewFarmBookmarkall.isSelected = false
//                1-> holder.imageviewNewFarmBookmarkall.isSelected = true
//            }
//        }
//
        if (mydataList[position].personLimit == mydataList[position].currentPerson) {
        }
//        if(mydataList[position].isBooked!=null){
//            when(mydataList[position].isBooked){
//                0-> holder.isBooked.isSelected = false
//                1-> holder.isBooked.isSelected = true
//            }
//        }
//        holder.starNum.text = mydataList[position].star.toString()


    }
}