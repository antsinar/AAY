package com.example.cookit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.util.Log.e
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
import java.io.IOException
import kotlin.random.Random


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
                val text = etSearch.text.toString().toLowerCase().trim()
                if (text == ""){
                    val toast = Toast.makeText(applicationContext, "Εισάγετε όρο αναζήτησης", Toast.LENGTH_SHORT)
                    toast.show()
                    return
                }
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

                    if (!responseBody?.isEmpty()!!){
                        val imageview : ImageView = findViewById(R.id.ivcookEat)
                        imageview.visibility = View.GONE
                    }

                    if(responseBody.isEmpty()){
                        val toast = Toast.makeText(applicationContext, "Δε βρέθηκαν αποτελέσματα", Toast.LENGTH_SHORT)
                        toast.show()
                    }


                    //So app does not crash
                    if (responseBody != null){

                        rowAdapter = RowAdapter(baseContext, responseBody)
                        rowAdapter.notifyDataSetChanged()
                        rvrecipeItems.adapter = rowAdapter
                    }
                    //nullable object causes app crash
                    else{
                        d("Null error","")
                    }
                }

                override fun onFailure(call: Call<List<Recipe>?>, t: Throwable) {
                    d("failed",""+t.message)
                    if(t is IOException){

                        val toast = Toast.makeText(applicationContext ,"Σιγουρευτείτε πως έχετε σύνδεση στο Ίντερνετ", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
            })
        }
}
