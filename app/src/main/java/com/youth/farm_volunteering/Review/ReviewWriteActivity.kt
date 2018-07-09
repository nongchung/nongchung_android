package com.youth.farm_volunteering.Review

import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.youth.farm_volunteering.R
import kotlinx.android.synthetic.main.activity_review_write.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.fragment_mypage.view.*


class ReviewWriteActivity : AppCompatActivity() {

    lateinit var ReviewItems : ArrayList<ReviewData>
    lateinit var ReviewAdapter : ReviewAdapter
    private var linearLayoutManager : LinearLayoutManager? = null

    private var REQ_CODE_SELECT_IMAGE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_write)

        ReviewItems = ArrayList()
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

                            ReviewItems.add(ReviewData(urione))
                        }
                    } else if(uri!=null){
                        ReviewItems.add(ReviewData(uri))
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

}
