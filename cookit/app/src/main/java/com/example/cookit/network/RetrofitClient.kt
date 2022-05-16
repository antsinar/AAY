package com.example.cookit.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    var retrofit: Retrofit? = null

    fun getRetrofitClient(mContext: Context): Retrofit? {
        if (retrofit == null) {
            val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                .addInterceptor(ConnectivityInterceptor(mContext))

            val BASEURL = "http://sintagesapi.herokuapp.com/api/"

            retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
        }
        return retrofit
    }
}