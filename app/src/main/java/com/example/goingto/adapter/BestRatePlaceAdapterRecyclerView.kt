package com.example.goingto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.goingto.R
import com.example.goingto.model.Place

class BestRatePlaceAdapterRecyclerView(val places: ArrayList<Place>,val navController: NavController) :
    RecyclerView.Adapter<BestRatePlaceAdapterRecyclerView.BestRatePlaceViewHolder>() {

    inner class BestRatePlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val name = itemView.findViewById<TextView>(R.id.title_place)
        val image = itemView.findViewById<ImageView>(R.id.image_place)
        val description = itemView.findViewById<TextView>(R.id.description_place)
        val rate = itemView.findViewById<TextView>(R.id.rate_place)
        var place: Place? = null
        fun setData(place: Place) {
            name.text = place.name
            image.setImageResource(R.drawable.place)
            description.text = place.description
            rate.text = place.rate.toString()
            this.place = place
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            navController.navigate(R.id.action_navigation_home_to_navigation_detail_place)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestRatePlaceViewHolder {
        return BestRatePlaceViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.prototype_best_rate_place,parent,false)
        )
    }

    override fun onBindViewHolder(holder: BestRatePlaceViewHolder, position: Int) {
        holder.setData(places[position])
    }

    override fun getItemCount(): Int {
        return places.size
    }
}