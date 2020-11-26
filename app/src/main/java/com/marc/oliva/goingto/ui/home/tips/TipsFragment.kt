package com.marc.oliva.goingto.ui.home.tips

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marc.oliva.goingto.adapter.TipAdapterRecyclerView
import com.example.goingto.model.Tip
import com.example.goingto.ui.home.tips.TipsViewModel
import com.marc.oliva.goingto.R
import kotlinx.android.synthetic.main.tips_fragment.*

class TipsFragment : Fragment() {

    companion object {
        fun newInstance() = TipsFragment()
    }

    private lateinit var viewModel: TipsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tips_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TipsViewModel::class.java)
        val tips = ArrayList<Tip>()
        tips.add(Tip("Lleva paraguas siempre en este lugar"))
        tips.add(Tip("No es necesario estar pendiente de tus pertenencias todo el tiempo"))
        tips.add(Tip("Si eres extranjero, revisa bien los precios"))
        tips.add(Tip("Si vas a viaje en avión desde Lima, pide un asiento a la derecha"))
        tips.add(Tip("Procura llevar siempre dos jerseys por si una se ensucia demasiado"))
        val tipAdapter = TipAdapterRecyclerView(tips)
        tips_recyclerview.adapter = tipAdapter

    }

}