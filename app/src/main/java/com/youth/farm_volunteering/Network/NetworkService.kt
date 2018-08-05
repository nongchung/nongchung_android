package com.youth.farm_volunteering.Network
import android.graphics.Bitmap
import android.net.Uri
import com.youth.farm_volunteering.Home.ThemaNonghwal.ThemaData
import com.youth.farm_volunteering.Home.applyResponseData
import com.youth.farm_volunteering.MyActivity.MyActivityResponseData
import com.youth.farm_volunteering.Review.PostReviewResponseData
import com.youth.farm_volunteering.Review.ReviewData
import com.youth.farm_volunteering.data.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
import java.io.File


interface NetworkService {
    @GET("api/home")
    fun home(
//            @Query("idx") idx: Int
    ): Call<HomeResponseData>

    //더보기(마이페이지)
    @GET("/api/mypage")
    fun mypage(): Call<MyPageResponseData>

//    @FormUrlEncoded
//    @HTTP(method = "PUT", path="/api/mypage/photo", hasBody=true)
//    fun myphoto(@Field("image") image: File): Call<PhotoData>

    @Multipart
    @PUT("/api/mypage/photo")
    fun change_photo(
            @Part image : MultipartBody.Part?
    ) : Call<MyPhoto>






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

    @POST("/api/review")
    fun postReview(
            @Body postReviewData : ReviewData
    ) : Callback<PostReviewResponseData>

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
    @POST("/api/bookmark")
    fun bookMark(@Field("nhIdx") nhIdx: Int): Call<BookmarkData>

    @FormUrlEncoded
    @POST("/api/signup")
    fun registration(@Field("email") email: String, @Field("password") password: String, @Field("nickname") nickname: String, @Field("name") name: String, @Field("sex") sex: Int, @Field("handphone") handphone: String, @Field("birth") birth: String): Call<DefaultResponseData>

    @FormUrlEncoded
    @PUT("/api/mypage/nickname")
    fun nickname(
//            @Part("nickname") nickname : String
        @Field ("nickname") nickname: String, @Field("newnickname") newnickname : String
    ): Call<NickNameResponseData>

    @FormUrlEncoded
    @PUT("/api/mypage/password")
    fun password(
            @Field("password") password : String, @Field("newpw") newpw : String
    ): Call<PasswordResourceData>

    @FormUrlEncoded
    @POST("/api/home/request")
    fun applyNh(@Field("nhIdx") nhIdx: Int, @Field("schIdx") schIdx: Int): Call<applyResponseData>

    @GET("/api/home/request/user")
    fun getUserInfo() : Call<UserResponseData>




    @FormUrlEncoded
    @HTTP(method = "DELETE", path="/api/bookmark", hasBody=true)
    fun delete(@Field("nhIdx") nhIdx: Int): Call<BookmarkData>
}