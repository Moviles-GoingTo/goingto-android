package com.marc.oliva.goingto.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView

import com.example.goingto.model.Place
import com.marc.oliva.goingto.R

class UnknowPlaceAdapterRecyclerView(val places: ArrayList<Place>,val navController: NavController) :
    RecyclerView.Adapter<UnknowPlaceAdapterRecyclerView.UnknowViewHolder>() {
    inner class UnknowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val image = itemView.findViewById<ImageView>(R.id.image_place)
        val name = itemView.findViewById<TextView>(R.id.name_place)
        val location = itemView.findViewById<TextView>(R.id.location_place)
        var place: Place? = null
        fun setData(place: Place) {
            image.setImageResource(R.drawable.place)
            name.text = place.name
            location.text = place.location
            this.place = place
        }

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            navController.navigate(R.id.action_navigation_home_to_navigation_detail_place)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnknowViewHolder {
        return UnknowViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.prototype_unknow_place, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UnknowViewHolder, position: Int) {
        holder.setData(places[position])
    }

    override fun getItemCount(): Int {
        return places.size
    }
}