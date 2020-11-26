package com.marc.oliva.goingto.ui.challenges

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marc.oliva.goingto.R
import com.marc.oliva.goingto.adapter.BenefitAdapterRecyclerView
import com.marc.oliva.goingto.adapter.ChallengAdapterRecyclerView
import com.marc.oliva.goingto.model.Challeng
import kotlinx.android.synthetic.main.challenges_fragment.*

class ChallengesFragment : Fragment() {

    companion object {
        fun newInstance() = ChallengesFragment()
    }

    private lateinit var viewModel: ChallengesViewModel
    private lateinit var challengRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.challenges_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChallengesViewModel::class.java)
        challengRecyclerView = rvChallenges
        loadChalleng(this)
    }

    private fun loadChalleng(challengFragment:ChallengesFragment) {
        val challenges= ArrayList<Challeng>();

        challenges.add(Challeng("Visitar Latinoamerica","Conoce las ciudades mas importantes de America del Sur",200.0))
        challenges.add(Challeng("Escribe una reseña","Escribe sobre tu experiencia en algún lugar!",50.0))
        challenges.add(Challeng("Canjea un beneficio","Elige el beneficio que más te guste y obtenlo con puntos",100.0))
        challenges.add(Challeng("Deja un tip","Las sugerencias ayudan a crecer a todos!",50.0))

        challengRecyclerView.layoutManager = LinearLayoutManager(context);
        challengRecyclerView.adapter = ChallengAdapterRecyclerView(challenges,context)
    }

}