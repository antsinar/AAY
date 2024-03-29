package com.example.cookit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_recipe)

        btnToSearch.setOnClickListener(View.OnClickListener {
            fun onClick(){
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
            onClick()

        })

        getIncomingIntent()
    }
    private fun getIncomingIntent(){

        if(intent.hasExtra("name") && intent.hasExtra("id")){
            val name: String? = intent.getStringExtra("name")
            val author: String? = intent.getStringExtra("author")
            val ing0: String? = intent.getStringExtra("ing0")
            val ing1: String? = intent.getStringExtra("ing1")
            val ing2: String? = intent.getStringExtra("ing2")
            val ing3: String? = intent.getStringExtra("ing3")
            val ing4: String? = intent.getStringExtra("ing4")
            val ing5: String? = intent.getStringExtra("ing5")
            val ing6: String? = intent.getStringExtra("ing6")
            val ing7: String? = intent.getStringExtra("ing7")
            val ing8: String? = intent.getStringExtra("ing8")
            val ing9: String? = intent.getStringExtra("ing9")
            val ing10: String? = intent.getStringExtra("ing10")
            val ing11: String? = intent.getStringExtra("ing11")
            val ing12: String? = intent.getStringExtra("ing12")
            val ing13: String? = intent.getStringExtra("ing13")
            val ing14: String? = intent.getStringExtra("ing14")
            val ing15: String? = intent.getStringExtra("ing15")
            val ing16: String? = intent.getStringExtra("ing16")
            val ing17: String? = intent.getStringExtra("ing17")
            val ing18: String? = intent.getStringExtra("ing18")
            val ing19: String? = intent.getStringExtra("ing19")
            val step0: String? = intent.getStringExtra("step0")
            val step1: String? = intent.getStringExtra("step1")
            val step2: String? = intent.getStringExtra("step2")
            val step3: String? = intent.getStringExtra("step3")
            val step4: String? = intent.getStringExtra("step4")
            val step5: String? = intent.getStringExtra("step5")
            val step6: String? = intent.getStringExtra("step6")
            val step7: String? = intent.getStringExtra("step7")

            val id: Int = intent.getIntExtra("id",1)

            setRecipe(name,author,ing0,ing1,ing2,ing3,ing4,ing5,ing6,ing7,ing8,ing9,ing10,ing11,ing12,ing13,ing14,ing15,ing16,ing17,ing18,ing19,step0,step1,step2,step3,step4,step5,step6,step7)

        }
    }

    private fun setRecipe(name: String?,author: String?,ing0: String?,ing1: String?,ing2: String?,ing3: String?,ing4: String?,ing5: String?,ing6: String?,ing7: String?,ing8: String?,ing9: String?,ing10: String?,ing11: String?,ing12: String?,ing13: String?,ing14: String?,ing15: String?,ing16: String?,ing17: String?,ing18: String?,ing19: String?,step0: String?,step1: String?,step2: String?,step3: String?,step4: String?,step5: String?,step6: String?,step7: String?){
        val tvRecipeName: TextView = findViewById(R.id.tvRecipeName)
        val tvAuthor: TextView = findViewById(R.id.tvAuthor)
        val tvIng0: TextView = findViewById(R.id.tvIng0)
        val tvIng1: TextView = findViewById(R.id.tvIng1)
        val tvIng2: TextView = findViewById(R.id.tvIng2)
        val tvIng3: TextView = findViewById(R.id.tvIng3)
        val tvIng4: TextView = findViewById(R.id.tvIng4)
        val tvIng5: TextView = findViewById(R.id.tvIng5)
        val tvIng6: TextView = findViewById(R.id.tvIng6)
        val tvIng7: TextView = findViewById(R.id.tvIng7)
        val tvIng8: TextView = findViewById(R.id.tvIng8)
        val tvIng9: TextView = findViewById(R.id.tvIng9)
        val tvIng10: TextView = findViewById(R.id.tvIng10)
        val tvIng11: TextView = findViewById(R.id.tvIng11)
        val tvIng12: TextView = findViewById(R.id.tvIng12)
        val tvIng13: TextView = findViewById(R.id.tvIng13)
        val tvIng14: TextView = findViewById(R.id.tvIng14)
        val tvIng15: TextView = findViewById(R.id.tvIng15)
        val tvIng16: TextView = findViewById(R.id.tvIng16)
        val tvIng17: TextView = findViewById(R.id.tvIng17)
        val tvIng18: TextView = findViewById(R.id.tvIng18)
        val tvIng19: TextView = findViewById(R.id.tvIng19)
        val tvStep0: TextView = findViewById(R.id.tvStep0)
        val tvStep1: TextView = findViewById(R.id.tvStep1)
        val tvStep2: TextView = findViewById(R.id.tvStep2)
        val tvStep3: TextView = findViewById(R.id.tvStep3)
        val tvStep4: TextView = findViewById(R.id.tvStep4)
        val tvStep5: TextView = findViewById(R.id.tvStep5)
        val tvStep6: TextView = findViewById(R.id.tvStep6)
        val tvStep7: TextView = findViewById(R.id.tvStep7)

        tvRecipeName.text = name
        tvAuthor.text = author

        tvIng0.text = ing0
        tvIng1.text = ing1
        tvIng2.text = ing2
        tvIng3.text = ing3
        tvIng4.text = ing4
        tvIng5.text = ing5
        tvIng6.text = ing6
        tvIng7.text = ing7
        tvIng8.text = ing8
        tvIng9.text = ing9
        tvIng10.text = ing10
        if(tvIng10.text == ""){
            tvIng10.visibility = View.GONE
        }
        tvIng11.text = ing11
        if(tvIng11.text == ""){
            tvIng11.visibility = View.GONE
        }
        tvIng12.text = ing12
        if(tvIng12.text == ""){
            tvIng12.visibility = View.GONE
        }
        tvIng13.text = ing13
        if(tvIng13.text == ""){
            tvIng13.visibility = View.GONE
        }
        tvIng14.text = ing14
        if(tvIng14.text == ""){
            tvIng14.visibility = View.GONE
        }
        tvIng15.text = ing15
        if(tvIng15.text == ""){
            tvIng15.visibility = View.GONE
        }
        tvIng16.text = ing16
        if(tvIng16.text == ""){
            tvIng16.visibility = View.GONE
        }
        tvIng17.text = ing17
        if(tvIng17.text == ""){
            tvIng17.visibility = View.GONE
        }
        tvIng18.text = ing18
        if(tvIng18.text == ""){
            tvIng18.visibility = View.GONE
        }
        tvIng19.text = ing19
        if(tvIng19.text == ""){
            tvIng19.visibility = View.GONE
        }
        tvStep0.text = step0
        tvStep1.text = step1
        tvStep2.text = step2
        tvStep3.text = step3
        tvStep4.text = step4
        tvStep5.text = step5
        if(tvStep5.text == ""){
            tvStep5.visibility = View.GONE
        }
        tvStep6.text = step6
        if(tvStep6.text == ""){
            tvStep6.visibility = View.GONE
        }
        tvStep7.text = step7
        if(tvStep7.text == ""){
            tvStep7.visibility = View.GONE
        }
    }


}

