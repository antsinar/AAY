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
import kotlinx.android.synthetic.main.recipe_item.view.*
import kotlinx.android.synthetic.main.row_item.view.*
import kotlinx.android.synthetic.main.row_item.view.linearLayout
import kotlinx.android.synthetic.main.view_recipe.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RowAdapter(val context: Context, val recipeList: List<Recipe>) : RecyclerView.Adapter<RowAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var name: TextView
        var summary: TextView
        var layout: LinearLayout

        init {

            name = itemView.tvname
            summary = itemView.tvsummary
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

                        intent.putExtra("name", recipeList[position].name)
                        intent.putExtra("id",recipeList[position].id)
                        intent.putExtra("ing0", recipeList[position].ing0)
                        intent.putExtra("ing1",recipeList[position].ing1)
                        intent.putExtra("ing2", recipeList[position].ing2)
                        intent.putExtra("ing3",recipeList[position].ing3)
                        intent.putExtra("ing4", recipeList[position].ing4)
                        intent.putExtra("ing5",recipeList[position].ing5)
                        intent.putExtra("ing6", recipeList[position].ing6)
                        intent.putExtra("ing7",recipeList[position].ing7)
                        intent.putExtra("ing8", recipeList[position].ing8)
                        intent.putExtra("ing9",recipeList[position].ing9)
                        intent.putExtra("ing10", recipeList[position].ing10)
                        intent.putExtra("ing11",recipeList[position].ing11)
                        intent.putExtra("ing12", recipeList[position].ing12)
                        intent.putExtra("ing13",recipeList[position].ing13)
                        intent.putExtra("ing14", recipeList[position].ing14)
                        intent.putExtra("ing15",recipeList[position].ing15)
                        intent.putExtra("ing16", recipeList[position].ing16)
                        intent.putExtra("ing17",recipeList[position].ing17)
                        intent.putExtra("ing18", recipeList[position].ing18)
                        intent.putExtra("ing19",recipeList[position].ing19)
                        intent.putExtra("step0", recipeList[position].step0)
                        intent.putExtra("step1",recipeList[position].step1)
                        intent.putExtra("step2", recipeList[position].step2)
                        intent.putExtra("step3",recipeList[position].step3)
                        intent.putExtra("step4", recipeList[position].step4)
                        intent.putExtra("step5",recipeList[position].step5)
                        intent.putExtra("step6", recipeList[position].step6)
                        intent.putExtra("step7",recipeList[position].step7)

                        context.startActivity(intent)
                    } else {
                        d("Null error", "")
                    }
                }

                override fun onFailure(call: Call<Recipe>, t: Throwable) {
                    Log.d("failed", "" + t.message)
                }
            })

        })


    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}