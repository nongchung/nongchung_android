package com.youth.farm_volunteering.review

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.youth.farm_volunteering.myactivity.MyActivityData
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.myactivity.GetBeforeModifyResponse
import com.youth.farm_volunteering.myactivity.MyReviewData
import kotlinx.android.synthetic.main.activity_review_write.*
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import kotlin.concurrent.thread

class ReviewWriteActivity : AppCompatActivity() {

    var ReviewPicData : ArrayList<Uri>? = null
    lateinit var ReviewAdapter : ReviewAdapter
    var multipartBodyList : ArrayList<MultipartBody.Part>? = null

    var editTextContent : EditText? = null

    var filePath : String? = null
    var fileList :ArrayList<File> = ArrayList()
    var name : RequestBody? = null
    var photoBody : RequestBody? = null
    var photo : String? = null
    var rIdx : Int? = null

    var reviewContent : String? = null
    var toolbar : Toolbar? = null
    private var linearLayoutManager : LinearLayoutManager? = null

    private var REQ_CODE_SELECT_IMAGE = 100
    var getMyActivityData : MyActivityData? = null
    var getMyReviewData : MyReviewData? = null

    var handler : Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_write)


        toolbar = findViewById(R.id.toolbar_review)
        editTextContent = findViewById(R.id.edittext_content)

        ReviewPicData = ArrayList()
        multipartBodyList = ArrayList()

        setSupportActionBar(toolbar)

        var dateFormat = SimpleDateFormat("yyyy.MM.dd")
        var dateFormatStart = SimpleDateFormat("yyyy년 MM월 dd일")
        var dateFormatEnd = SimpleDateFormat(" ~ dd일")

        getMyActivityData = intent.getParcelableExtra("MyActivityData")

        getWrittenReview(getMyActivityData!!.rState!!, getMyActivityData!!.idx!!)

        var parsedStartDate = dateFormat.parse(getMyActivityData!!.startDate)
        var parsedEndDate = dateFormat.parse(getMyActivityData!!.endDate)

        var startDate = dateFormatStart.format(parsedStartDate)
        var endDate = dateFormatEnd.format(parsedEndDate)

        ratingbar_star.setOnRatingBarChangeListener(object : RatingBar.OnRatingBarChangeListener{
            override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
                textview_star.text = rating.toString()
            }
        })


        textview_farm_name.text = getMyActivityData!!.name
        textview_farm_date.text = startDate + endDate + "(" + getMyActivityData!!.period + ")"

        //앨범에서 사진 가져오기
        album_button.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQ_CODE_SELECT_IMAGE)

        })

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    var uri : Uri? = data!!.data
                    var clipData : ClipData? = data.clipData

                    if(clipData!=null){
                        for(i in 0..clipData.itemCount-1){
                            var urione : Uri = clipData.getItemAt(i).uri

                            ReviewPicData!!.add(urione)     //여러 개
                        }
                    } else if(uri!=null){                       //한 개
                        ReviewPicData!!.add(uri)
                    }

                    ReviewAdapter = ReviewAdapter(ReviewPicData!!)
                    review_pic_rv.layoutManager = LinearLayoutManager(this)
                    review_pic_rv.adapter = ReviewAdapter
                    linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                    review_pic_rv!!.layoutManager = linearLayoutManager

                } catch (e: Exception) {
                    Log.e("test", e.message)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var menuInflater = getMenuInflater()
        menuInflater!!.inflate(R.menu.menu_review, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_complete -> {
                reviewContent = editTextContent!!.text.toString()
                var contentReqBody : RequestBody = RequestBody.create(MediaType.parse("text/plain"), reviewContent)
                var ratingBarStar: Float = ratingbar_star.rating

                when(getMyActivityData!!.rState)
                {
                    0-> {
                        makeMultipartBody()
                        postReview(contentReqBody!!, ratingBarStar)
                    }

                    1->{
                        modifyMultipartBody()
//                        makeMultipartBody()
                        putReview(rIdx!!,contentReqBody!!, ratingBarStar)
                    }
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getWrittenReview (rState : Int, schIdx : Int){
        if(rState == 1){
            val getBeforeModify = ApplicationController.instance!!.networkService!!.getBeforeModify(schIdx)
            getBeforeModify.enqueue(object : Callback<GetBeforeModifyResponse>{
                override fun onFailure(call: Call<GetBeforeModifyResponse>?, t: Throwable?) {
                    Toast.makeText(applicationContext, "Fail to Get ModifyReview", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<GetBeforeModifyResponse>?, response: Response<GetBeforeModifyResponse>?) {
                    if(response!!.code() == 200){
                        if(response!!.isSuccessful){
                            getMyReviewData = response!!.body().data
                            ratingbar_star.rating = getMyReviewData!!.star
                            edittext_content.setText(getMyReviewData!!.content)
                            rIdx = getMyReviewData!!.rIdx
                            if(getMyActivityData!!.img != null) {
                                for (i in 0 until getMyReviewData!!.img.size) {
                                    if(!(getMyReviewData!!.img[i].isEmpty())) {     //이미지를 불러왔을 때, ""값이 있으면(isEmpty()가 true면) ReviewPicData에 넣지않는다.
                                        ReviewPicData!!.add(i, Uri.parse(getMyReviewData!!.img[i]))
                                    }
                                }
                                ReviewAdapter = ReviewAdapter(ReviewPicData!!)
                                review_pic_rv.layoutManager = LinearLayoutManager(applicationContext)
                                review_pic_rv.adapter = ReviewAdapter
                                linearLayoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
                                review_pic_rv!!.layoutManager = linearLayoutManager
                            }
                        }
                    }
                }
            })
        }
    }

    private fun postReview(reviewContent : RequestBody, ratingBarStar : Float){
        val postReview = ApplicationController.instance!!.networkService!!.postReview(multipartBodyList!!,
                reviewContent, getMyActivityData!!.idx!!, ratingBarStar)
        postReview.enqueue(object : retrofit2.Callback<PostReviewResponseData> {
            override fun onFailure(call: Call<PostReviewResponseData>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Failed to Post Review", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PostReviewResponseData>?, response: Response<PostReviewResponseData>?) {
                if (response!!.code() == 200) {
                    Toast.makeText(applicationContext, "리뷰 작성 완료!", Toast.LENGTH_SHORT).show()
                    finish()
                } else if (response!!.code() == 400) {
                    Toast.makeText(applicationContext, "리뷰 중복 작성은 불가능합니다!", Toast.LENGTH_SHORT).show()
                    finish()
                } else if (response!!.code() == 500) {
                    Toast.makeText(applicationContext, "Internal Server Error", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })
    }
    
    private fun putReview(rIdx : Int, reviewContent: RequestBody, ratingBarStar: Float){
        val putReview = ApplicationController.instance!!.networkService!!.putReview(rIdx, multipartBodyList!!,
                reviewContent, getMyActivityData!!.idx!!, ratingBarStar)
        putReview.enqueue(object : Callback<PutReviewResponseData>{
            override fun onFailure(call: Call<PutReviewResponseData>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Failed to Put Review", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PutReviewResponseData>?, response: Response<PutReviewResponseData>?) {
                if(response!!.code() == 200){
                    if(response!!.isSuccessful){
                        Toast.makeText(applicationContext, "후기 수정 성공!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }

        })
    }
    
    private fun makeMultipartBody(){
        val options = BitmapFactory.Options()
        var input: InputStream? = null // here, you need to get your context.
        var bitmap : Bitmap? = null

        for (i in 0 until ReviewPicData!!.size){
            val baos = ByteArrayOutputStream()

            try {
                input = applicationContext.contentResolver.openInputStream(ReviewPicData!!.get(i))
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            bitmap = BitmapFactory.decodeStream(input, null, options)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)

            baos.close()

            photoBody = RequestBody.create(MediaType.parse("image/*"), baos.toByteArray())
//            photo = bitmap.toString()

            multipartBodyList!!.add(i, MultipartBody.Part.createFormData("rImages", "jpg", photoBody))
        }

    }

    private fun modifyMultipartBody(){
//        val options = BitmapFactory.Options()
//        var input: InputStream? = null // here, you need to get your context.
//        var bitmap : Bitmap? = null

        var file : File? = null
        var fileName : String? = null


        for (i in 0 until ReviewPicData!!.size){
            val baos = ByteArrayOutputStream()

//            var thread = Thread() {
//                run() {
//                    try {
//                        var url : URL = URL(ReviewPicData!![i].toString())
//                        val connection = url.openConnection()
//                        connection.doInput = true
//                        connection.connect()
//                        val input = connection.getInputStream()
//                        bitmap = BitmapFactory.decodeStream(input)
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//                }
//            }

//            thread.start()
//            bitmap = getBitmapFromURL(ReviewPicData!![i].toString())
//            bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, baos)

//            baos.close()

            file = File(ReviewPicData!![i].path)
            fileName = file.name

//            photoBody = RequestBody.create(MediaType.parse("image/*"), baos.toByteArray())
            photoBody = RequestBody.create(MediaType.parse("image/*"), file)
//            photo = bitmap.toString()

            multipartBodyList!!.add(i, MultipartBody.Part.createFormData("rImages", fileName, photoBody))
        }

    }

    private fun getRealPathFromURIPath(contentURI: Uri, context: Context): String {
        val cursor = context.contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            return contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }

    fun getBitmapFromURL(src: String): Bitmap? {
        try {
            val url = URL(src)
            val connection = url.openConnection()
            connection.doInput = true
            connection.connect()
            val input = connection.getInputStream()
            return BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

    }
}
