package com.youth.farm_volunteering.Network

import com.youth.farm_volunteering.data.*
import retrofit2.http.FormUrlEncoded

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

import com.youth.farm_volunteering.data.MyPageResponseData
import com.youth.farm_volunteering.data.DetailNonghwalResponseData
import retrofit2.Call


import retrofit2.http.*


interface NetworkService {
    @GET("api/home")
    fun home(
//            @Query : idx
    ): Call<HomeResponseData>

    @GET("/api/mypage")
    fun mypage(): Call<MyPageResponseData>

    @GET("/api/home/detail/nh")
    fun detailnonghwal(
            @Query("idx") idx:Int
    ): Call<DetailNonghwalResponseData>

    @GET("/api/home/detail/review")
    fun review(
            @Query("scheIdx") scheIdx: Int
    ): Call<ReviewResponseData>

    @FormUrlEncoded
    @POST("/api/signin")
    fun login(@Field("email") first: String, @Field("password") last: String): Call<LoginResponseData>


}