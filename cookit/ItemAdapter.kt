package com.example.cookit

import android.content.Context
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookit.network.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*
import kotlinx.android.synthetic.main.row_item.view.*
import kotlinx.android.synthetic.main.row_item.view.linearLayout

class ItemAdapter(val context: Context, val recipe: Recipe) :  RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var text: TextView
        var layout: LinearLayout

        init {
            text = itemView.tvtext
            layout = itemView.linearLayout
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.recipe_item,parent,false)
        return ItemAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        holder.text.text = recipe.ing0
        d("onbindviewholder",""+recipe.ing0)
    }

    override fun getItemCount(): Int {
        return 1
    }


}