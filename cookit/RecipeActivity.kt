package com.example.cookit

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.Log.d
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookit.network.ApiInterface
import com.example.cookit.network.Recipe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_recipe.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeActivity : AppCompatActivity(){
    lateinit var recipeAdapter: ItemAdapter
    lateinit var LinearLayoutManager: LinearLayoutManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_recipe)


        rvRecipeActivity.setHasFixedSize(true)
        LinearLayoutManager = LinearLayoutManager(this)
        rvRecipeActivity.layoutManager = LinearLayoutManager

        getIncomingIntent(intent)
    }
    private fun getIncomingIntent(intent: Intent){

        if(intent.hasExtra("name") && intent.hasExtra("id")){
            val name: String? = intent.getStringExtra("name")

            val id: Int = intent.getIntExtra("id",1)

            setRecipe(name)
            getDetails(id)
        }
    }

    private fun setRecipe(name: String?){
        val tvRecipeName: TextView = findViewById(R.id.tvRecipeName)

        tvRecipeName.text = name
    }

    private fun getDetails(id: Int){
        val BASE_URL = "https://sintagesapi.herokuapp.com/api/"
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getDetails(id)
        retrofitData.enqueue(object : Callback<Recipe> {
            override fun onResponse(
                call: Call<Recipe>,
                response: Response<Recipe>
            ) {
                val responseBody = response.body()

                //So app does not crash
                if (responseBody != null) {
                    recipeAdapter = ItemAdapter(baseContext, responseBody)
                    recipeAdapter.notifyDataSetChanged()
                    rvRecipeActivity.adapter = recipeAdapter
                } else {
                    d("Null error", "")
                }
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                Log.d("failed", "" + t.message)
            }
        })
    }


}

