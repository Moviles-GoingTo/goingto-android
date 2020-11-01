package com.marc.oliva.goingto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goingto.model.Benefit
import com.marc.oliva.goingto.R
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.prototype_benefit.view.*

class BenefitAdapterRecyclerView(val benefits: List<Benefit>, val context: Context?): RecyclerView.Adapter<BenefitAdapterRecyclerView.ViewHolder>()  {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val ivPlace = view.ivPlace;
        val tvPlaceName = view.tvPlaceName;
        val tvPlaceCountry = view.tvPlaceCountry;
        val btPoints = view.btPoints;
        val tvRate = view.tvRate;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitAdapterRecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.prototype_benefit, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return benefits.size
    }

    override fun onBindViewHolder(holder: BenefitAdapterRecyclerView.ViewHolder, position: Int) {
        val benefit = benefits[position]
        holder.tvPlaceName.text = benefit.placeName;
        holder.tvPlaceCountry.text = benefit.placeLocation;
        holder.btPoints.text = benefit.points.toString();
        holder.tvRate.text = benefit.rate.toString();

        //imagen
        val picBuilder = Picasso.Builder(context!!)
        picBuilder.downloader(OkHttp3Downloader(context))
        picBuilder.build().load(benefit.imageUrl).into(holder.ivPlace)
    }
}