package com.example.cookit

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.cookit.network.ApiInterface
import com.example.cookit.network.Recipe
import kotlinx.android.synthetic.main.row_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RowAdapter(val context: Context, val recipeList: List<Recipe>) : RecyclerView.Adapter<RowAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView
        var summary: TextView
        var id: TextView
        var layout: LinearLayout
        init {
            name = itemView.tvname
            summary = itemView.tvsummary
            id = itemView.tvid
            layout = itemView.linearLayout
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = recipeList[position].name
        holder.summary.text = recipeList[position].summary
        holder.id.text = recipeList[position].id.toString()
        holder.layout.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(context, RecipeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            val BASE_URL = "https://sintagesapi.herokuapp.com/api/"
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)

            val retrofitData = retrofitBuilder.getDetails(recipeList[position].id)
            retrofitData.enqueue(object : Callback<Recipe> {
                override fun onResponse(
                    call: Call<Recipe>,
                    response: Response<Recipe>
                ) {
                    val responseBody = response.body()

                    //So app does not crash
                    if (responseBody != null) {
                        intent.putExtra("recipe", responseBody.toString())
                        d("clicked recipe",""+responseBody.toString())

                    } else {
                        d("Null error", "")
                    }
                }

                override fun onFailure(call: Call<Recipe>, t: Throwable) {
                    Log.d("failed", "" + t.message)
                }
            })
            context.startActivity(intent)
        })


    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}