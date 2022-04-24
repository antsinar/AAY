package com.example.cookit

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log.d
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cookit.network.Recipe

class RecipeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_recipe)

        getIncomingIntent(intent)
    }
    private fun getIncomingIntent(intent: Intent){
        d("intent hasExtra",""+intent.hasExtra("recipe"))
        if(intent.hasExtra("recipe")){
            val recipe: String? = intent.getStringExtra("recipe")
            d("inside Intent",""+recipe)
            setRecipe(recipe)
        }


    }

    private fun setRecipe(recipe: String?){
        val tvRecipe: TextView = findViewById(R.id.tvRecipe)
        d("inside setrecipe",""+recipe)
        tvRecipe.text = recipe
    }
}