package com.example.goingto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goingto.R
import com.example.goingto.model.Challeng
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.prototype_benefit.view.*
import kotlinx.android.synthetic.main.prototype_challenges.view.*

class ChallengAdapterRecyclerView(val challenges: List<Challeng>, val context: Context?): RecyclerView.Adapter<ChallengAdapterRecyclerView.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvNameChallenge = view.tvNameChallenge;
        val tvDescription = view.tvDescription;
        val tvPoints = view.tvPoints;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengAdapterRecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.prototype_challenges, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return challenges.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val challeng = challenges[position]
        holder.tvDescription.text = challeng.description;
        holder.tvNameChallenge.text = challeng.name;
        holder.tvPoints.text = challeng.points.toString();

    }
}