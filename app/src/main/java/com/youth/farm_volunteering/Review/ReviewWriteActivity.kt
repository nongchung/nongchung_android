package com.youth.farm_volunteering.Review

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.youth.farm_volunteering.MyActivity.MyActivityData
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_review_write.*
import java.text.SimpleDateFormat

//import kotlinx.android.synthetic.main.fragment_mypage.*
//import kotlinx.android.synthetic.main.fragment_mypage.view.*


class ReviewWriteActivity : AppCompatActivity() {

    lateinit var ReviewItems : ArrayList<ReviewData>
    lateinit var ReviewAdapter : ReviewAdapter
    var reviewContent : String? = null
    var toolbar : Toolbar? = null
    private var linearLayoutManager : LinearLayoutManager? = null

    private var REQ_CODE_SELECT_IMAGE = 100
    var getMyActivityData : MyActivityData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_write)

        toolbar = findViewById(R.id.toolbar_review)

        setSupportActionBar(toolbar)

        var dateFormat = SimpleDateFormat("yyyy-MM-dd")
        var dateFormatStart = SimpleDateFormat("yyyy년 MM월 dd일")
        var dateFormatEnd = SimpleDateFormat(" ~ dd일")

        var parsedStartDate = dateFormat.parse(getMyActivityData!!.startDate)
        var parsedEndDate = dateFormat.parse(getMyActivityData!!.endDate)

        var startDate = dateFormatStart.format(parsedStartDate)
        var endDate = dateFormatEnd.format(parsedEndDate)

        ReviewItems = ArrayList()
        getMyActivityData = intent.getParcelableExtra("MyActivityData")
        textview_farm_name.text = getMyActivityData!!.name
        textview_farm_date.text = startDate + endDate + "(" + getMyActivityData!!.period + ")"
//        ReviewItems.add(ReviewData(R.drawable.image_1))
//        ReviewItems.add(ReviewData(R.drawable.image_2))
//        ReviewItems.add(ReviewData(R.drawable.image_3))
//        ReviewItems.add(ReviewData(R.drawable.image_4))
//        ReviewItems.add(ReviewData(R.drawable.image_5))



        //앨범에서 사진 가져오기
        album_button.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQ_CODE_SELECT_IMAGE)

        })

//        album_button.setOnClickListener(View.OnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
//            intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
//        })
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

//                            ReviewItems.add(ReviewData(urione))
                        }
                    } else if(uri!=null){
//                        ReviewItems.add(ReviewData(uri))
                    }

//                    var bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, data!!.data)

//                    ReviewItems.add(ReviewData(bitmap)) //recyclerview에 넣어야함

                    ReviewAdapter = ReviewAdapter(ReviewItems)
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
                reviewContent = edittext_content.text.toString()

//                var postReview = ApplicationController.instance!!.networkService!!.postReview(ReviewData())
//                postReview
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
