package com.youth.farm_volunteering.MyPage

//import com.youth.farm_volunteering.Home.ThemaNonghwal.ThemaActivity
//import com.youth.farm_volunteering.Home.ThemaNonghwal.ThemaActivity
import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix

import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.gridlayout.R.attr.orientation
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.*
import com.youth.farm_volunteering.data.*
import com.youth.farm_volunteering.login.LoginActivity
import com.youth.farm_volunteering.login.LoginToken
import com.youth.farm_volunteering.mypage.ChangeNicknameActivity
import com.youth.farm_volunteering.mypage.ChangePasswordActivity
import com.youth.farm_volunteering.mypage.FaqActivity
import com.youth.farm_volunteering.mypage.NoticeActivity
import kotlinx.android.synthetic.main.activity_signup1.*
import kotlinx.android.synthetic.main.fragment_mypage_1.*
import kotlinx.android.synthetic.main.fragment_mypage_1.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.util.Collections.rotate
import kotlin.text.Typography.degree


class MypageFragment : Fragment(), EasyPermissions.PermissionCallbacks {
    private var REQ_CODE_SELECT_IMAGE = 100
    lateinit var data: Uri
//    private var image: MultipartBody.Part? = null
    private var selectedImage: Uri? = null
    var myPageData: MyPageData? = null
    var image: File? = null
    var photostring : String? = null
    var countnumber : Int = 100
    var countnumber2 : Int = 100



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_mypage_1, container, false)

        if (LoginToken.logined) {

            var mypageCall = ApplicationController.instance!!.networkService!!.mypage();
            mypageCall.enqueue(object : Callback<MyPageResponseData> {
                override fun onFailure(call: Call<MyPageResponseData>, t: Throwable?) {
                    Toast.makeText(activity!!, "통신상태를 점검해주세요!", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<MyPageResponseData>, response: Response<MyPageResponseData>) {
                    mypage_profile.visibility = View.VISIBLE

                    myPageData = response.body().data!!.get(0)
                    invalidate()
                }
            })
        } else {
            var i = Intent(activity, LoginActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(i)
        }
        //내 정보 프레그먼트 밑에 있는 계정, 설정, 지원 전부 다 ImageView로 박은다음에 토글 키가 있는 설정은 RelativeLayout으로 두고 match_parent를 가지는
        //ImageView의 background를 '푸시알림'으로 두고 토글키를 오른쪽 끝에다가 alignRight해주자

        v.layout_mypoint.setOnClickListener{
            Toast.makeText(this.activity.applicationContext, "구현 예정입니다!", Toast.LENGTH_SHORT).show()
        }

        v.layout_myreviews.setOnClickListener {
            Toast.makeText(this.activity.applicationContext, "구현 예정입니다!", Toast.LENGTH_SHORT).show()

        }

        //프로필 사진 변경
        v.imageview_mypage_profile.setOnClickListener(View.OnClickListener {

            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, REQ_CODE_SELECT_IMAGE)

        })

        //닉네임 변경
        v.nickname_change_button.setOnClickListener(View.OnClickListener {
            //            Toast.makeText(this.activity.applicationContext, "구현 예정입니다!", Toast.LENGTH_SHORT).show()
            var v = Intent(this.activity.applicationContext, ChangeNicknameActivity::class.java)
            startActivityForResult(v,101)
        })



        //비밀번호 변경
        v.password_change_button.setOnClickListener(View.OnClickListener {
//            Toast.makeText(this.activity.applicationContext, "구현 예정입니다!", Toast.LENGTH_SHORT).show()
            var v = Intent(this.activity.applicationContext, ChangePasswordActivity::class.java)
            startActivity(v)
        })

        //푸쉬알림설정
        v.push_button.setOnClickListener(View.OnClickListener {
            Toast.makeText(this.activity.applicationContext, "구현 예정입니다!", Toast.LENGTH_SHORT).show()
//            var v = Intent(this.activity.applicationContext, PushActivity::class.java)
//            startActivity(v)
        })
        v.notice_button.setOnClickListener(View.OnClickListener {
            var v = Intent(this.context, NoticeActivity::class.java)
            startActivity(v)
        })
        v.faq_button.setOnClickListener(View.OnClickListener {
            var v = Intent(this.activity.applicationContext, FaqActivity::class.java)
            startActivity(v)
        })

        v.layout_mypage_logout.setOnClickListener {
            //            Toast.makeText(activity!!, "프로토타입버전에선 항상 로그인이 되어있습니다.", Toast.LENGTH_SHORT).show()
            LoginToken.token = null
            var sharedPreference = activity.getSharedPreferences(LoginToken.PREF_KEY, Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.remove(LoginToken.PREF_KEY)
            LoginToken.logined = false
            editor.commit()
            Toast.makeText(activity!!, "로그아웃에 성공하였습니다.", Toast.LENGTH_SHORT).show()
            var v = Intent(this.activity.applicationContext, LoginActivity::class.java)
            startActivity(v)

        }
        return v
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        Log.d(TAG, "Permission has been denied");
    }

    private fun getRealPathFromURIPath(contentURI: Uri, activity: Activity): String {
        val cursor = activity.contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            return contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }



    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (selectedImage != null) {
            var filePath = getRealPathFromURIPath(selectedImage!!, MypageFragment@ this.activity);
            var file = File(filePath);
            val mFile = RequestBody.create(MediaType.parse("image/*"), file)
            //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);
            val fileToUpload = MultipartBody.Part.createFormData("file", file.name, mFile)


            Log.d("photoa", "1")

            var photo_change = ApplicationController.instance!!.networkService!!.image(fileToUpload,mFile)
//                    change_photo(fileToUpload)
            Log.d("photoa", "2")
            photo_change.enqueue(object : Callback<MyPhoto> {

                override fun onFailure(call: Call<MyPhoto>, t: Throwable?) {
                    Log.d("photoa", "3")
                    Toast.makeText(activity, "photo request fail", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<MyPhoto>, response: Response<MyPhoto>) {
                    Log.d("aaa", response!!.code().toString())
                    Log.d("aaa", response!!.message())
                    if (response!!.isSuccessful) {

                        if (response!!.body().message == "success To change photo") {
                            Toast.makeText(activity, "성공일거야", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {


//                    val input : InputStream? = null/* : InputStream = activity.contentResolver.openInputStream(dataMy!!.getDataMy())*/
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data
//
                    val options = BitmapFactory.Options()
//
                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = context.contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    var bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    input!!.close()
//                    val bmRotated = rotateBitmap(bitmap, orientation)

                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/*"), baos.toByteArray())
                    val photo = this.data.toString()
//                    val photo = File(this.dataMy.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다
                    val body : MultipartBody.Part = MultipartBody.Part.createFormData("image", photo, photoBody)


//                    RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
//                     MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!
                    selectedImage = data!!.data;
                    if (EasyPermissions.hasPermissions(this.activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        var filePath = getRealPathFromURIPath(selectedImage!!, MypageFragment@ this.activity);
                        var file = File(filePath)
                        val name = RequestBody.create(MediaType.parse("image/*"), file.name)
                        var degree : Int = getExifOrientation(filePath)

//                        if (degree != 0) {
//                            Log.i(TAG, "Rotating... " + degree);
//                            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//                            val matrix = Matrix()
//                            matrix.postRotate(-degree.toFloat());
//                            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
//                                    bitmap.getHeight(), matrix, true);
//                        }
//                        val body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);
//                        val fileToUpload = MultipartBody.Part.createFormData("file", file.name, mFile)
//                        Glide.with(this)
//                                .load(dataMy!!.dataMy)
//                                .into(imageview_mypage_profile)

                        Log.d("photoa", "1")

                        var photo_change = ApplicationController.instance!!.networkService!!.image(body, name)
//                                change_photo(fileToUpload)
                        Log.d("photoa", "2")
                        photo_change.enqueue(object : Callback<MyPhoto> {

                            override fun onFailure(call: Call<MyPhoto>, t: Throwable?) {
                                Log.d("photoa", "3")
                                Toast.makeText(activity, "photo request fail", Toast.LENGTH_SHORT).show()
                            }

                            override fun onResponse(call: Call<MyPhoto>, response: Response<MyPhoto>) {
                                Log.d("aaa", response!!.code().toString())
                                Log.d("aaa", response!!.message())
                                if (response!!.isSuccessful) {


//                                    bitmap = rotate(bitmap, degree)


                                    // 변환된 이미지 사용

//                                    if(countnumber==100){
//                                        imageview_mypage_profile.setImageBitmap(bitmap)
//                                    }

                                    if(degree == 90 && countnumber == 50 && countnumber2==20){
                                        imageview_mypage_profile.setImageBitmap(bitmap)
                                    }
                                    else{

                                    }
                                    if(degree == 90 && countnumber == 100){
                                        if(countnumber2==100){
                                            bitmap = rotate(bitmap, degree)
                                            countnumber2=30
                                        }
                                       else if(countnumber2==30){
                                            bitmap = rotate(bitmap, degree)
                                        }
                                       else if(countnumber2==20){
                                            imageview_mypage_profile.setImageBitmap(bitmap)
                                        }
                                        else{

                                        }

                                    }
                                    else{

                                    }
                                    if(degree == 0){
                                        if(countnumber2==20) {
                                            bitmap = rotate(bitmap, 270)
                                            imageview_mypage_profile.setImageBitmap(bitmap)
                                            countnumber2 = 100
                                        }
                                            else if(countnumber2==30){

                                                countnumber2=100

                                        }

                                       else{

                                        }
                                    }
                                    else{

                                    }

                                    imageview_mypage_profile.setImageBitmap(bitmap)


//                                    imageview_mypage_profile.setImageBitmap(bitmap)
//                                    response.body().dataMy = bitmap.toString()


                                    if (response!!.body().message == "success To change photo") {

//                                        Toast.makeText(activity, "성공일거야", Toast.LENGTH_SHORT).show()

//                                       getRotatedBitmap(bitmap, degree) // 이미지가 회전되어 나오는거 방지하기 위해 만듬

//                                        Glide.with(activity)
//                                                .load(response.body().dataMy)
//                                                .into(
//                                                        if(degree == 90 || degree == 180){
//
//
//                                                            // 이미지가 회전되어 나오는거 방지하기 위해 만듬
////                                                            rotateBitmap(bitmap, orientation) // 1번째방법
////                                                              bitmap = rotateBitmap(bitmap, degree) // 2번째방법 이게 왜안될까? 진짜.....
//                                                            bitmap = rotateImage(bitmap,90)
//                                                            imageview_mypage_profile.setImageBitmap(bitmap)
//
//
//
////                                                            imageview_mypage_profile.rotation = 90f
////                                                            imageview_mypage_profile.setRotationX(-90.toFloat()) // 3번째방법
////                                                            imageview_mypage_profile.setRotationY(-90.toFloat()) // 4번째방법
////                                                            imageview_mypage_profile.setImageBitmap(rotateBitmap(bitmap,degree)) // 5번째방법
////                                                            getRotatedBitmap(bitmap, degree) // 6번째방법
//
//                                                            imageview_mypage_profile
//
//                                                        }
//                                                        else{
//                                                            imageview_mypage_profile
//                                                        }
//                                                )
//                                        bitmap = rotateBitmap(bitmap,degree)
//                                        imageview_mypage_profile.setImageBitmap(bitmap)
                                    }
                                }
                            }
                        })
                    } else {
                        EasyPermissions.requestPermissions(this, "This app needs access to your file storage so that it can read photos.", 300, Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }

    fun rotate(bitmap: Bitmap?, degrees: Int): Bitmap? {
        var bitmap = bitmap
        if (degrees != 0 && bitmap != null) {
            val m = Matrix()
            m.setRotate(degrees.toFloat(), bitmap.width.toFloat() / 2,
                    bitmap.height.toFloat() / 2)

            try {
                val converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.width, bitmap.height, m, true)
                if (bitmap != converted) {
                    bitmap.recycle()
                    bitmap = converted
                }
            } catch (ex: OutOfMemoryError) {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }

        }
        return bitmap
    }
    // 이미지를 특정 각도로 회전하는 함수입니다

    fun rotateImage(source: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        Toast.makeText(this.activity.applicationContext, "구현 예정입니다!", Toast.LENGTH_SHORT).show()
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height,
                matrix, true)
    }




//    override fun onActivityResult(requestCode: Int, resultCode: Int, dataMy: Intent?) {
//        if (requestCode == REQ_CODE_SELECT_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//                try {
//                    val bitmap = MediaStore.Images.Media.getBitmap(this.activity.contentResolver, dataMy!!.dataMy)
//                    imageview_mypage_profile.setImageBitmap(bitmap)
//
//                    //put해주기
//                    var photo = ApplicationController.instance!!.networkService!!.myphoto(bitmap)
//                    photo.enqueue(object : Callback<PhotoData> {
//                        override fun onFailure(call: Call<PhotoData>, t: Throwable?) {
//                            Toast.makeText(activity, "photo request fail", Toast.LENGTH_SHORT).show()
//                        }
//                        override fun onResponse(call: Call<PhotoData>, response: Response<PhotoData>) {
//                            if(response!!.isSuccessful){
//                                if(response!!.body().message == "success To change photo"){
//                                    Toast.makeText(activity, "성공일거야", Toast.LENGTH_SHORT).show()
//                                }
//                            }
//                        }
//                    })
//
//                } catch (e: Exception) {
//                    Log.e("test", e.message)
//                }
//            }
//        }
//    }

    // 이미지를 특정 각도로 회전하는 함수입니다

    fun rotateBitmap(bitmap: Bitmap, orientation : Int): Bitmap? {


        val matrix = Matrix()
        when (orientation) {

            ExifInterface.ORIENTATION_NORMAL -> return bitmap
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> matrix.setScale(-1.toFloat(), 1.toFloat())
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.setRotate(180.toFloat())
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> {
                matrix.setRotate(180.toFloat())
                matrix.postScale(-1.toFloat(), 1.toFloat())
            }
            ExifInterface.ORIENTATION_TRANSPOSE -> {
                matrix.setRotate(90.toFloat())
                matrix.postScale(-1.toFloat(), 1.toFloat())
            }
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.setRotate(90.toFloat())
            ExifInterface.ORIENTATION_TRANSVERSE -> {
                matrix.setRotate(-90.toFloat())
                matrix.postScale(-1.toFloat(), 1.toFloat())
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.setRotate(-90.toFloat())
            else -> return bitmap
        }
        try {

            val bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            bitmap.recycle()
            return bmRotated
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
            return null
        }

    }

    //이미지의 Orientation 정보를 얻는 함수입니다.

    private fun getExifOrientation(filePath: String): Int {
        var exif: ExifInterface? = null

        try {
            exif = ExifInterface(filePath)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (exif != null) {
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL)

            if (orientation != -1) {
                when (orientation) {
                    ExifInterface.ORIENTATION_ROTATE_90 -> return 90

                    ExifInterface.ORIENTATION_ROTATE_180 -> return 180

                    ExifInterface.ORIENTATION_ROTATE_270 -> return 270
                }
            }
        }

        return 0
    }

    // 이미지를 특정 각도로 회전하는 함수입니다

    private fun getRotatedBitmap(bitmap: Bitmap?, degree: Int): Bitmap? {
        var bitmap = bitmap

        if (degree != 0 && bitmap != null) {
            val matrix = Matrix()
            matrix.setRotate(degree.toFloat(), bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2)

            try {
                val tmpBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)


                if (bitmap != tmpBitmap) {
                    bitmap.recycle()
                    bitmap = tmpBitmap
                }
                imageview_mypage_profile.setImageBitmap(tmpBitmap)
            } catch (e: OutOfMemoryError) {
                e.printStackTrace()
            }

        }

        return bitmap
    }


    fun invalidate() {
//        val options = BitmapFactory.Options()
////
//        var input: InputStream? = null // here, you need to get your context.
//        try {
//            input = context.contentResolver.openInputStream(this.dataMy)
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        }
//

////        var file = File(filePath)
////        var bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
        var degree : Int = getExifOrientation(myPageData!!.img!!)
//
//       bitmap = rotate(bitmap, degree)
//
//
//        // 변환된 이미지 사용
//        imageview_mypage_profile.setImageBitmap(bitmap)
        if(countnumber2==30){
            imageview_mypage_profile.setRotation(90.toFloat())
            countnumber2 = 20
        }
        else if(countnumber2==20){
            imageview_mypage_profile.setRotation(90.toFloat())
        }

        Glide.with(activity)
                .load(myPageData!!.img)
                .into(imageview_mypage_profile)
        textview_mypage_email.setText(myPageData!!.mail)
        textview_mypage_nickname.setText(myPageData!!.nickname)
        textview_name.setText(myPageData!!.name + " / " + myPageData!!.age + "세")
    }




}
