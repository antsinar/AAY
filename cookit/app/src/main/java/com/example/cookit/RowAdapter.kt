package com.example.cookit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookit.network.Recipe
import kotlinx.android.synthetic.main.row_item.view.*

class RowAdapter(val context: Context, val recipeList: List<Recipe>) : RecyclerView.Adapter<RowAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var summary: TextView
        init {
            name = itemView.tvname
            summary = itemView.tvsummary
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = recipeList[position].name.toString()
        holder.summary.text = recipeList[position].summary
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}