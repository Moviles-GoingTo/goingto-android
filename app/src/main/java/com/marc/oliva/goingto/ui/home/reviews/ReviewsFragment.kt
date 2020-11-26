package com.marc.oliva.goingto.ui.home.reviews

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marc.oliva.goingto.adapter.ReviewsAdapterRecyclerView
import com.example.goingto.model.Review
import com.example.goingto.ui.home.reviews.ReviewsViewModel
import com.marc.oliva.goingto.R
import kotlinx.android.synthetic.main.reviews_fragment.*

class ReviewsFragment : Fragment() {

    companion object {
        fun newInstance() = ReviewsFragment()
    }

    private lateinit var viewModel: ReviewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reviews_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReviewsViewModel::class.java)
        val reviews = ArrayList<Review>()
        reviews.add(Review("Juan Esbozo","Bonito lugar para visitar",""))
        reviews.add(Review("Sergio Lara","Fui con la familia y pasamos un maravilloso momento",""))
        reviews.add(Review("Vanessa Tucuman","Paz, calma y tranquilidad...",""))
        reviews.add(Review("David Quevedo","A este lugar no le falta nada",""))
        reviews.add(Review("Sebastian Cornejo","Hace mucho frío, pero hermosos paisajes",""))
        reviews.add(Review("Silvia Guevara","Excelente lugar para ir de vacaciones",""))
        val reviewsAdapter = ReviewsAdapterRecyclerView(reviews)
        reviews_recyclerview.adapter= reviewsAdapter
    }

}