package com.example.goingto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goingto.R

import com.example.goingto.model.Tip

class TipAdapterRecyclerView(val tips: ArrayList<Tip>) :
    RecyclerView.Adapter<TipAdapterRecyclerView.TipViewHolder>() {
    inner class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description = itemView.findViewById<TextView>(R.id.description_tip_textview)
        fun setData(tip: Tip){
            description.text = tip.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        return TipViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.prototype_tip,parent,false))
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.setData(tips[position])
    }

    override fun getItemCount(): Int {
        return  tips.size
    }
}