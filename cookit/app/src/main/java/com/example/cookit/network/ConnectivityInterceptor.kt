package com.example.cookit.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(context : Context) : Interceptor {

    private var mContext : Context = context

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain) : Response {
        if(!isConnected()){
            throw IOException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    fun isConnected() :Boolean{
        val connectivityManager : ConnectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo : NetworkInfo = connectivityManager.activeNetwork as NetworkInfo
        return (netInfo != null && netInfo.isConnected())

    }

}