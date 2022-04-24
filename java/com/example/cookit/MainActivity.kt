package com.example.cookit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookit.network.ApiInterface
import com.example.cookit.network.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var rowAdapter: RowAdapter
    lateinit var LinearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get data from editText
        var btnSearch: Button
        var etSearch: EditText

        btnSearch = findViewById(R.id.btnSearchRecipe)
        etSearch = findViewById(R.id.etSearchIng)


        //set recycler view
        rvrecipeItems.setHasFixedSize(true)
        LinearLayoutManager = LinearLayoutManager(this)
        rvrecipeItems.layoutManager = LinearLayoutManager

        //onclicklistener
        btnSearch.setOnClickListener(View.OnClickListener() {
            fun onClick() {
                val text = etSearch.text.toString()
                Log.v("Button pressed",""+text)
                getRecipes(text)
            }
            onClick()
        })


    }
        //API Call
        private fun getRecipes(text:String){
            val BASE_URL = "https://sintagesapi.herokuapp.com/api/"
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)

            //getRecipe from ApiInterface
            val retrofitData = retrofitBuilder.getRecipe(text)
            retrofitData.enqueue(object : Callback<List<Recipe>> {
                override fun onResponse(
                    call: Call<List<Recipe>>,
                    response: Response<List<Recipe>>
                ) {
                    val responseBody = response.body()

                    //So app does not crash
                    if (responseBody != null){
                        rowAdapter = RowAdapter(baseContext, responseBody)
                        rowAdapter.notifyDataSetChanged()
                        rvrecipeItems.adapter = rowAdapter
                    }
                    else{
                        d("Null error","")
                    }
                }

                override fun onFailure(call: Call<List<Recipe>?>, t: Throwable) {
                    d("failed",""+t.message)
                }
            })
        }
}
