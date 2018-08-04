package com.youth.farm_volunteering.MyPage

//import com.youth.farm_volunteering.Home.ThemaNonghwal.ThemaActivity
//import com.youth.farm_volunteering.Home.ThemaNonghwal.ThemaActivity
import android.Manifest
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.asksira.loopingviewpagerdemo.ApplicationController
import com.bumptech.glide.Glide
import com.youth.farm_volunteering.R
import com.youth.farm_volunteering.R.id.*
import com.youth.farm_volunteering.data.MyPageData
import com.youth.farm_volunteering.data.MyPageResponseData
import com.youth.farm_volunteering.data.MyPhoto
import com.youth.farm_volunteering.login.LoginActivity
import com.youth.farm_volunteering.login.LoginToken
import kotlinx.android.synthetic.main.fragment_mypage_1.*
import kotlinx.android.synthetic.main.fragment_mypage_1.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class MypageFragment : Fragment(), EasyPermissions.PermissionCallbacks {
    private var REQ_CODE_SELECT_IMAGE = 100
    lateinit var data: Uri
    private var image: MultipartBody.Part? = null
    private var selectedImage: Uri? = null
    var myPageData: MyPageData? = null

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
            Toast.makeText(this.activity.applicationContext, "구현 예정입니다!", Toast.LENGTH_SHORT).show()
//            val photoPickerIntent = Intent(Intent.ACTION_PICK)
//            photoPickerIntent.type = "image/png"
//            startActivityForResult(photoPickerIntent, REQ_CODE_SELECT_IMAGE)
        })

        //닉네임 변경
        v.nickname_change_button.setOnClickListener(View.OnClickListener {
            //            Toast.makeText(this.activity.applicationContext, "구현 예정입니다!", Toast.LENGTH_SHORT).show()
            var v = Intent(this.activity.applicationContext, ChangeNicknameActivity::class.java)
            startActivity(v)
        })
        //비밀번호 변경
        v.password_change_button.setOnClickListener(View.OnClickListener {
            Toast.makeText(this.activity.applicationContext, "구현 예정입니다!", Toast.LENGTH_SHORT).show()
//            var v = Intent(this.activity.applicationContext, ChangePasswordActivity::class.java)
//            startActivity(v)
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
            editor.commit()
            Toast.makeText(activity!!, "로그아웃에 성공하였습니다.", Toast.LENGTH_SHORT).show()

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

            var photo_change = ApplicationController.instance!!.networkService!!.change_photo(fileToUpload)
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
                    //if(ApplicationController.getInstance().is)
//                    this.data = data!!.data
//
//                    val options = BitmapFactory.Options()
//
//                    var input: InputStream? = null // here, you need to get your context.
//                    try {
//                        input = context.contentResolver.openInputStream(this.data)
//                    } catch (e: FileNotFoundException) {
//                        e.printStackTrace()
//                    }
//
//                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
//                    val baos = ByteArrayOutputStream()
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
//                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
//                    val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다
//                    val photo = this.data.toString()
//                    /RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
                    // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!
                    selectedImage = data!!.getData();
                    if (EasyPermissions.hasPermissions(this.activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        var filePath = getRealPathFromURIPath(selectedImage!!, MypageFragment@ this.activity);
                        var file = File(filePath);
                        val mFile = RequestBody.create(MediaType.parse("image/*"), file)
                        //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);
                        val fileToUpload = MultipartBody.Part.createFormData("file", file.name, mFile)
                        Glide.with(this)
                                .load(data!!.data)
                                .into(imageview_mypage_profile)

                        Log.d("photoa", "1")

                        var photo_change = ApplicationController.instance!!.networkService!!.change_photo(fileToUpload)
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
                    } else {
                        EasyPermissions.requestPermissions(this, "This app needs access to your file storage so that it can read photos.", 300, Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQ_CODE_SELECT_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//                try {
//                    val bitmap = MediaStore.Images.Media.getBitmap(this.activity.contentResolver, data!!.data)
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

    fun invalidate() {
        Glide.with(activity)
                .load(myPageData!!.img)
                .into(imageview_mypage_profile)
        textview_mypage_email.setText(myPageData!!.mail)
        textview_mypage_nickname.setText(myPageData!!.name)
        textview_name.setText(myPageData!!.name + " / " + myPageData!!.age + "세")
    }
}