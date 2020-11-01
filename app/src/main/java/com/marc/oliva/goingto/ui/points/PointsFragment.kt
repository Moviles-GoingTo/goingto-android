package com.marc.oliva.goingto.ui.points

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goingto.model.Benefit
import com.example.goingto.ui.points.PointsViewModel
import com.marc.oliva.goingto.R
import com.marc.oliva.goingto.adapter.BenefitAdapterRecyclerView
import kotlinx.android.synthetic.main.points_fragment.*

class PointsFragment : Fragment() {

    companion object {
        fun newInstance() = PointsFragment()
    }

    private lateinit var viewModel: PointsViewModel
    private lateinit var benefitRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.points_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PointsViewModel::class.java)
        benefitRecyclerView = rvBenefits
        loadBenefits(this)
        // TODO: Use the ViewModel
    }

    private fun loadBenefits(pointsFragment: PointsFragment) {
        val benefits = ArrayList<Benefit>();
        benefits.add(Benefit("https://i0.wp.com/exitosanoticias.pe/v1/wp-content/uploads/2020/04/restaurantes-1.png", "Casa Portuguesa Prata", "Peru", 1200.00, 5.0));
        benefits.add(Benefit("https://i0.wp.com/exitosanoticias.pe/v1/wp-content/uploads/2020/04/restaurantes-1.png", "La Prata", "Brasil", 1400.00, 5.0));
        benefits.add(Benefit("https://i0.wp.com/exitosanoticias.pe/v1/wp-content/uploads/2020/04/restaurantes-1.png", "Portuguesa de la Prata", "Colombia", 1000.00, 5.0));

        benefitRecyclerView.layoutManager = LinearLayoutManager(context);
        benefitRecyclerView.adapter = BenefitAdapterRecyclerView(benefits, context)

    }

}