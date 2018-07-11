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

    @GET("/api/home/more/moreNew?idx=6")
    fun all_new(): Call<AllNewResponseData>

    @GET("/api/bookmark")
    fun like(): Call<LikeResponseData>

    @GET("/api/home/detail/nh")
    fun detailnonghwal(
            @Query("idx") idx: Int
    ): Call<DetailNonghwalResponseData>

    @GET("/api/review")
    fun review(
            @Query("scheIdx") scheIdx: Int
    ): Call<ReviewResponseData>

    @FormUrlEncoded
    @POST("/api/signin")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<LoginResponseData>

    @FormUrlEncoded
    @POST("/api/signup")
    fun registration(@Field("email") email: String, @Field("password") password: String, @Field("nickname") nickname: String, @Field("name") name: String, @Field("sex") sex: Int, @Field("handphone") handphone: String, @Field("birth") birth: String): Call<DefaultResponseData>

    @Multipart
    @PUT("/api/mypage/nickname")
    fun nickname(
            @Part("nickname") nickname : String
//        @Body nickname: String
    ): Call<NickNameResponseData>


}