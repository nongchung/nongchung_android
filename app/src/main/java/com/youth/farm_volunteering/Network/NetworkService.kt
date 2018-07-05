package com.youth.farm_volunteering.Network

import com.youth.farm_volunteering.data.HomeData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface NetworkService {
    @GET("api/home")
    fun home(): Call<HomeData>

}