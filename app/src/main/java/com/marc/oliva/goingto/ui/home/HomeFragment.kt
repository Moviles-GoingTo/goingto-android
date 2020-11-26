package com.marc.oliva.goingto.ui.home

import android.content.Context
import android.content.SharedPreferences
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

        val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val namePref: String? = sharedPreferences?.getString("name", "Miguelito")

        welcome_textview.text = "Bienvenido " + namePref

        val listPlaces = ArrayList<Place>()
        listPlaces.add(Place("Machu Picchu", "El Santuario Histórico de Machu Picchu es el destino turístico más visitado de Perú y de América",5.0,"Peru, Cusco"))
        listPlaces.add(Place("Sacsayhuamán","Sacsayhuamán (en quechua Saqsaywaman, de saqsay, lleno o satisfecho, y waman, halcón, es decir, halcón satisfecho) es una fortaleza ceremonial inca ubicada dos kilómetros al norte de la ciudad de Cusco.",4.8,"Peru, Cusco"))
        listPlaces.add(Place("Lineas de Nazca", "Las líneas de Nazca y geoglifos de pampas de Jumana o simplemente líneas de nazca son antiguos geoglifos que se encuentran en las pampas de Jumana, en el desierto de Nazca,",4.6,"Peru, Nazca"))
        listPlaces.add(Place("Volcan Misti", "El Misti es un estratovolcán al sur del Perú, cerca de la ciudad de Arequipa, considerado como un volcán activo.",4.7,"Peru, Arequipa"))
        val navController = findNavController()
        val bestAdapter = BestRatePlaceAdapterRecyclerView(listPlaces,navController)
        val unknowAdapter = UnknowPlaceAdapterRecyclerView(listPlaces,navController)
        best_places_recyclerview.adapter = bestAdapter
        unknow_places_recyclerview.adapter = unknowAdapter
    }
}