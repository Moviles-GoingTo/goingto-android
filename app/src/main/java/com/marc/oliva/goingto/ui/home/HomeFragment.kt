package com.marc.oliva.goingto.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.marc.oliva.goingto.adapter.BestRatePlaceAdapterRecyclerView
import com.marc.oliva.goingto.adapter.UnknowPlaceAdapterRecyclerView
import com.example.goingto.model.Place
import com.example.goingto.ui.home.HomeViewModel
import com.marc.oliva.goingto.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /*val tips = ArrayList<Tip>()
        tips.add(Tip("Tip uno"))
        val reviews = ArrayList<Review>()
        reviews.add(Review("Revi uno"))*/
        val listPlaces = ArrayList<Place>()
        listPlaces.add(Place("Lugar Uno",resources.getString(R.string.lorem_ipsum) ,5.0,"London"))
        listPlaces.add(Place("Lugar Uno", resources.getString(R.string.lorem_ipsum),5.0,"London"))
        listPlaces.add(Place("Lugar Uno", resources.getString(R.string.lorem_ipsum),5.0,"London"))
        listPlaces.add(Place("Lugar Uno",resources.getString(R.string.lorem_ipsum),5.0,"London"))
        listPlaces.add(Place("Lugar Uno", resources.getString(R.string.lorem_ipsum),5.0,"London"))
        listPlaces.add(Place("Lugar Uno", resources.getString(R.string.lorem_ipsum),5.0,"London"))
        val navController = findNavController()
        val bestAdapter = BestRatePlaceAdapterRecyclerView(listPlaces,navController)
        val unknowAdapter = UnknowPlaceAdapterRecyclerView(listPlaces,navController)
        best_places_recyclerview.adapter = bestAdapter
        unknow_places_recyclerview.adapter = unknowAdapter

    }
}