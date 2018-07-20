package com.youth.farm_volunteering.Home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.youth.farm_volunteering.R

class IntroThemeFarmAdapter(var dataList: List<Int>) : RecyclerView.Adapter<IntroThemeFarmItemViewHolder>(){
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroThemeFarmItemViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmintrotheme, parent, false)
        return IntroThemeFarmItemViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: IntroThemeFarmItemViewHolder, position: Int) {

        holder.imageviewFarmThemeIntro.setImageResource(dataList[position]!!)


        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context, "구현 예정입니다!", Toast.LENGTH_SHORT).show()
//            val intent = Intent(holder.itemView.context, ThemaActivity::class.java)
//            intent.putExtra("themePosition", position)
//            intent.putExtra("themeImage", dataList[position])
//            //추천수
//            //설명
//            holder.itemView.context.startActivity(intent)
        }
    }

}