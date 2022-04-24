package com.example.cookit.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("recipe-search/{text}/")
    fun getRecipe(@Path("text") text:String): Call<List<Recipe>>

    @GET("recipe-detail/{id}/")
    fun getDetails(@Path("id") id:Int) : Call<Recipe>


}