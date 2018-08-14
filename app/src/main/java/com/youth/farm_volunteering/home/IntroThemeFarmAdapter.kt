package com.youth.farm_volunteering.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.ShowAllFragment
import com.youth.farm_volunteering.home.ThemaNonghwal.ThemaActivity

class IntroThemeFarmAdapter(var dataList: List<Int>, var fragmentManager: FragmentManager) : RecyclerView.Adapter<IntroThemeFarmItemViewHolder>(){


    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroThemeFarmItemViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmintrotheme, parent, false)
        return IntroThemeFarmItemViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: IntroThemeFarmItemViewHolder, position: Int) {

        holder.imageviewFarmThemeIntro.setImageResource(dataList[position]!!)

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, ThemaActivity::class.java)
            intent.putExtra("title", "테마별 농활")
            intent.putExtra("themePosition",position)
            intent.putExtra("themeImage", dataList[position])

            holder.itemView.context.startActivity(intent)

//            val fragment = ThemaFragment()
//            val args = Bundle()
//
//            args.putString("title", "테마별 농활")
//            args.putInt("themePosition", position)
//            args.putInt("themeImage", dataList[position])
//
//            fragment.setArguments(args)
//            fragmentManager.beginTransaction()
//                    .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right,R.anim.enter_from_right,R.anim.exit_to_right)
//                    .addToBackStack(null).add(R.id.activity_main_container, fragment)
//                    .commit()
        }
    }

    fun setOnItemClickListener(l : View.OnClickListener){

    }

}