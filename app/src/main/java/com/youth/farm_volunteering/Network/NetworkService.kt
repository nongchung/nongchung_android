package com.youth.farm_volunteering.Network

import com.youth.farm_volunteering.data.HomeResponseData
<<<<<<< HEAD
=======
import com.youth.farm_volunteering.data.LoginResponseData
import com.youth.farm_volunteering.data.MyPageData
>>>>>>> 10d57d00d21817dae2a0287d06f8f6469742d35b
import com.youth.farm_volunteering.data.MyPageResponseData
import com.youth.farm_volunteering.data.DetailNonghwalResponseData
import retrofit2.Call
<<<<<<< HEAD
import retrofit2.http.GET
import retrofit2.http.Query
=======
import retrofit2.http.*
>>>>>>> 10d57d00d21817dae2a0287d06f8f6469742d35b


interface NetworkService {
    @GET("api/home")
    fun home(
//            @Query : idx
    ): Call<HomeResponseData>

    @GET("/api/mypage")
    fun mypage(): Call<MyPageResponseData>

<<<<<<< HEAD
    @GET("/api/home/detail/nh")
    fun detailnonghwal(
            @Query("idx") idx:Int
    ): Call<DetailNonghwalResponseData>
=======
    @FormUrlEncoded
    @POST("/api/signin")
    fun login(@Field("email") first: String, @Field("password") last: String): Call<LoginResponseData>
>>>>>>> 10d57d00d21817dae2a0287d06f8f6469742d35b

}