package com.example.goingto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goingto.R
import com.example.goingto.model.Review

class ReviewsAdapterRecyclerView(val reviews: ArrayList<Review>) :
    RecyclerView.Adapter<ReviewsAdapterRecyclerView.ReviewViewHolder>() {
    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameUser = itemView.findViewById<TextView>(R.id.name_user_textview)
        fun setData(review: Review){
            nameUser.text = review.nameUser
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.prototype_review,parent,false))
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.setData(reviews[position])
    }

    override fun getItemCount(): Int {
       return  reviews.size
    }
}