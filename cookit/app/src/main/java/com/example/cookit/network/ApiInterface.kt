package com.example.cookit.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiInterface {

    @GET("{text}")
    fun getRecipe(@Path("text") text:String): Call<List<Recipe>>
}