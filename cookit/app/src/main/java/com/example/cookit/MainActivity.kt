package com.example.cookit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookit.network.ApiInterface
import com.example.cookit.network.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException


class MainActivity : AppCompatActivity() {
    lateinit var rowAdapter: RowAdapter
    lateinit var LinearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnSearch: Button
        var etSearch: EditText
        btnSearch = findViewById(R.id.btnSearchRecipe)
        etSearch = findViewById(R.id.etSearchIng)
        val text = etSearch.text.toString()

        rvrecipeItems.setHasFixedSize(true)
        LinearLayoutManager = LinearLayoutManager(this)
        rvrecipeItems.layoutManager = LinearLayoutManager

        btnSearch.setOnClickListener(View.OnClickListener() {
            fun onClick() {

                Log.v("Button pressed","$text")
                getRecipes(text)
            }
            onClick()
        })


    }
        private fun getRecipes(text:String){
            val BASE_URL = "https://sintagesapi.herokuapp.com/api/recipe-search/"
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)

            val retrofitData = retrofitBuilder.getRecipe(text)
            retrofitData.enqueue(object : Callback<List<Recipe>?> {
                override fun onResponse(
                    call: Call<List<Recipe>?>,
                    response: Response<List<Recipe>?>
                ) {
                    val responseBody = response.body()
                    rowAdapter = RowAdapter(baseContext, responseBody!!)
                    rowAdapter.notifyDataSetChanged()
                    rvrecipeItems.adapter = rowAdapter
                }

                override fun onFailure(call: Call<List<Recipe>?>, t: Throwable) {
                    d("failed",""+t.message)
                }
            })
        }
}