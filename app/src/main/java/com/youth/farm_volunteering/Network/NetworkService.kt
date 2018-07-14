package com.youth.farm_volunteering.Network
import com.youth.farm_volunteering.Home.ThemaNonghwal.ThemaData
import com.youth.farm_volunteering.Home.applyResponseData
import com.youth.farm_volunteering.data.*
import retrofit2.http.*
import com.youth.farm_volunteering.MyActivity.MyActivityResponseData
import com.youth.farm_volunteering.data.*
import retrofit2.Call
import retrofit2.http.*


interface NetworkService {
    @GET("api/home")
    fun home(
//            @Query("idx") idx: Int
    ): Call<HomeResponseData>

    //더보기(마이페이지)
    @GET("/api/mypage")
    fun mypage(): Call<MyPageResponseData>


    @GET( "api/activity")
    fun myactivity(

    ): Call<MyActivityResponseData>

    @GET("api/home/detail/farm")
    fun farmprofile(
            @Query("idx") idx: Int
    ): Call<FarmProfileResponseData>

    @GET("api/home/theme/{idx}")
    fun thema(
            @Path("idx") idx : Int
    ): Call<ThemaData>

    //모두보기
    @GET("/api/home/more/moreNew?idx=6")
    fun all_new(): Call<AllNewResponseData>

    @GET("/api/home/more/moreNew")
    fun allnew(
            @Query("idx") idx: Int
    ): Call<WeekNonghwalAllData>

    //좋아요리스트
    @GET("/api/bookmark")
    fun like(): Call<LikeResponseData>

    //상세보기

    @GET("/api/home/detail/nh")
    fun detailnonghwal(
            @Query("idx") nhIdx: Int
    ): Call<DetailNonghwalResponseData>

    @GET("/api/search")
    fun search(
            @Query("start") startDate: String,
            @Query("end") endDate: String,
            @Query("person") personCount: Int,
            @Query("scontent") content : String,
            @Query("area") areaArrayString : String
    ): Call<SearchResponseData>
    @GET("/api/review")
    fun review(
            @Query("nhIdx") nhIdx: Int
    ): Call<ReviewResponseData>

    @GET("/api/home/more/moreNew")
    fun new(
    @Query("idx") idx : Int
    ): Call<AllNewResponseData>

    @GET("/api/home/more/morePopul")
    fun popular(
            @Query("idx") idx : Int
    ):Call<PopularResponseData>

    @FormUrlEncoded
    @POST("/api/signin")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<LoginResponseData>

    @FormUrlEncoded
    @POST("/api/dup-email")
    fun emailDup(@Field("email") email: String): Call<DupResponseData>

    @FormUrlEncoded
    @POST("/api/dup-nickname")
    fun nickDup(@Field("nickname") nick: String): Call<DupResponseData>

    @FormUrlEncoded
    @POST("/api/signup")
    fun registration(@Field("email") email: String, @Field("password") password: String, @Field("nickname") nickname: String, @Field("name") name: String, @Field("sex") sex: Int, @Field("handphone") handphone: String, @Field("birth") birth: String): Call<DefaultResponseData>

    @Multipart
    @PUT("/api/mypage/nickname")
    fun nickname(
//            @Part("nickname") nickname : String
        @Part ("nickname") nickname: String
    ): Call<NickNameResponseData>

    @Multipart
    @PUT("api/mypage/password")
    fun password(
            @Part("password") password : String, @Part("newpw") newpw : String
    ): Call<PasswordResourceData>

    @FormUrlEncoded
    @POST("api/home/request")
    fun applyNh(
            @Field("nhIdx") nhIdx : Int,
            @Field("schIdx") schIdx : Int
    ) : Call<applyResponseData>
}