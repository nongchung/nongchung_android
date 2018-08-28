package com.youth.farm_volunteering.home

import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.youth.farm_volunteering.FarmDetailActivity
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.data.BookmarkData
import com.youth.farm_volunteering.data.HomeNonghwalData
import com.youth.farm_volunteering.login.LoginActivity
import com.youth.farm_volunteering.login.LoginDialog
import com.youth.farm_volunteering.login.LoginToken
import com.youth.farm_volunteering.signup.SignupActivity1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewFarmAdapter(var dataList: List<HomeNonghwalData>) : RecyclerView.Adapter<NewFarmItemViewHolder>() {
    var loginDialog : LoginDialog? = null

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewFarmItemViewHolder {
        val mainView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_farmnew, parent, false)
        return NewFarmItemViewHolder(mainView)
    }

    override fun onBindViewHolder(holder: NewFarmItemViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(dataList[position].img)
                .apply(RequestOptions().placeholder(R.drawable.loading_big_image))
                .into(holder.imageviewNewFarm)
        holder.textviewNewFarmAddr.setText(dataList[position].addr)
        holder.textviewNewFarmDate.setText(dataList[position].period)
        holder.textviewNewFarmPrice.text = dataList[position].price.toString()
        holder.textviewNewFarmTitle.setText(dataList[position].name)


        if (dataList[position].isBooked != null) {
            when (dataList[position].isBooked) {
                0 -> holder.imageviewNewFarmBookmark.isSelected = false
                1 -> holder.imageviewNewFarmBookmark.isSelected = true
            }
        }

        holder.imageviewNewFarmBookmark.setOnClickListener {

            if(LoginToken.logined) {
                if (dataList[position].isBooked == 0) {
                    var bookMark = ApplicationController.instance!!.networkService!!.bookMark(Integer.parseInt(dataList[position].nhIdx.toString()))
                    bookMark.enqueue(object : Callback<BookmarkData> {
                        override fun onFailure(call: Call<BookmarkData>?, t: Throwable?) {
                            Toast.makeText(holder.itemView.context, "bookmark request fail", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<BookmarkData>?, response: Response<BookmarkData>?) {
                            Log.d("aaa", response!!.body().message)

                            if (response!!.body().message == "Success to Add") {
                                holder.imageviewNewFarmBookmark.isSelected = true
                                dataList[position].isBooked = 1
                            } else if (response!!.body().message == "Already Exist") {
                                Toast.makeText(holder.itemView.context, "이미 북마크에 저장하였습니다", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
                }
                if (dataList[position].isBooked == 1) {
                    var delete = ApplicationController.instance!!.networkService!!.delete(Integer.parseInt(dataList[position].nhIdx.toString()))
                    delete.enqueue(object : Callback<BookmarkData> {
                        override fun onFailure(call: Call<BookmarkData>?, t: Throwable?) {
                            Toast.makeText(holder.itemView.context, "bookmark request fail", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<BookmarkData>?, response: Response<BookmarkData>?) {
                            if (response!!.body().message == "Success to Delete") {
                                holder.imageviewNewFarmBookmark.isSelected = false
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

//                override fun onResponse(call: Call<BookmarkData>?, response: Response<BookmarkData>?) {
//
////                    Log.d("aaa",response!!.body().message)
////                    Log.d("aaa",response!!.body().toString())
////                    if (response!!.body().message == "Success to Add") {
//                        holder.imageviewNewFarmBookmark.isSelected = true
//                    } else if (response!!.body().message == "Already Exist") {
//                        Toast.makeText(holder.itemView.context, "이미 북마크에 저장하였습니다", Toast.LENGTH_SHORT).show()
//                    }
//                }

//            })
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FarmDetailActivity::class.java)
            intent.putExtra("populData", dataList[position] as Parcelable)

            //추천수
            //설명
            holder.itemView.context.startActivity(intent)
        }
    }
}