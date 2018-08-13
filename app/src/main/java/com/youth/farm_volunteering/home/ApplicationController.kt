package com.asksira.loopingviewpagerdemo

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.facebook.drawee.backends.pipeline.Fresco
import com.youth.farm_volunteering.network.NetworkService
import com.youth.farm_volunteering.PersistentCookieStore
import com.youth.farm_volunteering.login.LoginToken
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit


class ApplicationController : Application() {

    var networkService: NetworkService? = null
        private set
    val baseUrl = "http://13.125.216.198:3000/"
    var appContext: Context? = null
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        Fresco.initialize(this)
        instance = this

        buildNetwork()
    }

    fun buildNetwork() {

        val cookieStore = PersistentCookieStore(instance)
        val cookieManager = CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL)
        var sharedPreference = getSharedPreferences(LoginToken.PREF_KEY, Context.MODE_PRIVATE);
        LoginToken.token = sharedPreference.getString(LoginToken.PREF_KEY, null);

        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient().newBuilder().addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        var original = chain.request();

                        var request = original.newBuilder()
                                .header("token", if (LoginToken.token == null) "" else LoginToken.token)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                }).connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).cookieJar(JavaNetCookieJar(cookieManager)).build())
                .build()

        networkService = retrofit.create(NetworkService::class.java)
    }

    fun makeToast(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        var instance: ApplicationController? = null
    }
}
