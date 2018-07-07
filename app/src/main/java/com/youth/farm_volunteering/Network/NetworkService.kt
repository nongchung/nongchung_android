package com.youth.farm_volunteering.Network

import com.youth.farm_volunteering.data.HomeResponseData
import com.youth.farm_volunteering.data.LoginResponseData
import com.youth.farm_volunteering.data.MyPageData
import com.youth.farm_volunteering.data.MyPageResponseData
import com.youth.farm_volunteering.login.LoginToken
import retrofit2.Call
import retrofit2.http.*


interface NetworkService {
    @GET("api/home")
    fun home(): Call<HomeResponseData>

    @GET("/api/mypage")
    fun mypage(): Call<MyPageResponseData>

    @FormUrlEncoded
    @POST("/api/signin")
    fun login(@Field("email") first: String, @Field("password") last: String): Call<LoginResponseData>

}