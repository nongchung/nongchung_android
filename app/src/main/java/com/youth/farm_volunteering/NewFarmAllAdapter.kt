package com.youth.farm_volunteering

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.youth.farm_volunteering.home.NewFarmAllViewHolder
import com.youth.farm_volunteering.data.AllNewData
import com.youth.farm_volunteering.data.BookmarkData
import com.youth.farm_volunteering.data.NonghwalData
import com.youth.farm_volunteering.login.LoginActivity
import com.youth.farm_volunteering.login.LoginDialog
import com.youth.farm_volunteering.login.LoginToken
import com.youth.farm_volunteering.signup.SignupActivity1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewFarmAllAdapter(var dataList: List<NonghwalData>) : RecyclerView.Adapter<NewFarmAllViewHolder>(){
    var loginDialog : LoginDialog? = null

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewFarmAllViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmnew_all, parent, false)
        return NewFarmAllViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: NewFarmAllViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(dataList[position].img)
                .apply(RequestOptions().placeholder(R.drawable.user1_temp))
                .thumbnail(0.1f)
                .into(holder.imageviewNewFarmall)
        holder.textviewNewFarmAddrall.setText(dataList[position].addr)
        holder.textviewNewFarmDateall.setText(dataList[position].period)
        holder.textviewNewFarmPriceall.text = dataList[position].price.toString()
        holder.textviewNewFarmTitleall.setText(dataList[position].name)
        if(dataList[position].isBooked!=null){
            when(dataList[position].isBooked){
                0-> holder.imageviewNewFarmBookmarkall.isSelected = false
                1-> holder.imageviewNewFarmBookmarkall.isSelected = true
            }
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("populData", dataList[position])
            intent.putExtra("is_from_showall", true)

            //추천수
            //설명
            holder.itemView.context.startActivity(intent)
        }

        holder.imageviewNewFarmBookmarkall.setOnClickListener {
            if(LoginToken.logined) {
                if (dataList[position].isBooked == 0) {
                    var bookMark = ApplicationController.instance!!.networkService!!.bookMark(Integer.parseInt(dataList[position].getRealId().toString()))
                    bookMark.enqueue(object : Callback<BookmarkData> {
                        override fun onFailure(call: Call<BookmarkData>?, t: Throwable?) {
                            Toast.makeText(holder.itemView.context, "bookmark request fail", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<BookmarkData>?, response: Response<BookmarkData>?) {
                            if (response!!.body().message == "Success to Add") {
                                holder.imageviewNewFarmBookmarkall.isSelected = true
                                dataList[position].isBooked = 1
                            } else if (response!!.body().message == "Already Exist") {
                                Toast.makeText(holder.itemView.context, "이미 북마크에 저장하였습니다", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
                }

                if (dataList[position].isBooked == 1) {
                    var delete = ApplicationController.instance!!.networkService!!.delete(Integer.parseInt(dataList[position].getRealId().toString()))
                    delete.enqueue(object : Callback<BookmarkData> {
                        override fun onFailure(call: Call<BookmarkData>?, t: Throwable?) {
                            Toast.makeText(holder.itemView.context, "bookmark request fail", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<BookmarkData>?, response: Response<BookmarkData>?) {
                            if (response!!.body().message == "Success to Delete") {
                                holder.imageviewNewFarmBookmarkall.isSelected = false
                                dataList[position].isBooked = 0
                            } else if (response!!.body().message == "No nonghwal activity") {
                                Toast.makeText(holder.itemView.context, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(holder.itemView.context, response!!.body().message, Toast.LENGTH_SHORT).show()
                            }

                        }
                    })


                }
            } else{
                val loginOkListener = View.OnClickListener {
                    val intent = Intent(holder.itemView.context, LoginActivity::class.java)
                    loginDialog!!.dismiss()
                    holder.itemView.context.startActivity(intent)
                }

                val loginCancelListener = View.OnClickListener {
                    loginDialog!!.dismiss()
                }

                val signUpListener = View.OnClickListener {
                    val intent = Intent(holder.itemView.context, SignupActivity1::class.java)
                    loginDialog!!.dismiss()
                    holder.itemView.context.startActivity(intent)
                }

                loginDialog = LoginDialog(holder.itemView.context, loginOkListener, loginCancelListener, signUpListener)
                loginDialog!!.show()
            }
        }
//        holder.itemView.setOnClickListener{
//            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
//            intent.putExtra("populData", dataList[position] as Parcelable)
//
//
//            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//
//            //추천수
//            //설명
//            holder.itemView.context.startActivity(intent)
//        }
    }
}