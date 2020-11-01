package com.marc.oliva.goingto.ui.home.place_detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goingto.adapter.OptionsPlaceAdapterViewPager
import com.example.goingto.ui.home.place_detail.PlaceDetailViewModel
import com.marc.oliva.goingto.R
import kotlinx.android.synthetic.main.place_detail_fragment.*

class PlaceDetailFragment : Fragment() {

    companion object {
        fun newInstance() = PlaceDetailFragment()
    }

    private lateinit var viewModel: PlaceDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.place_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlaceDetailViewModel::class.java)
        options_place_viewpager.adapter = OptionsPlaceAdapterViewPager(fragmentManager,context)
        options_place_tablayout.setupWithViewPager(options_place_viewpager)



    }

}