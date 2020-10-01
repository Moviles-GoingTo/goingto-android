package com.example.goingto.ui.challenges

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goingto.R
import com.example.goingto.adapter.BenefitAdapterRecyclerView
import com.example.goingto.adapter.ChallengAdapterRecyclerView
import com.example.goingto.model.Challeng

class ChallengesFragment : Fragment() {

    companion object {
        fun newInstance() = ChallengesFragment()
    }

    private lateinit var viewModel: ChallengesViewModel
    private lateinit var challengRecycleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.challenges_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChallengesViewModel::class.java)
        loadChalleng(this)
    }

    private fun loadChalleng(challengFragment:ChallengesFragment) {
        val challenges= ArrayList<Challeng>();

        challenges.add(Challeng("Visitar Latinoamerica","Conoce las ciudades mas importantes de America del Sur",200.0))
        challenges.add(Challeng("Visitar Latinoamerica","Conoce las ciudades mas importantes de America del Sur",200.0))
        challenges.add(Challeng("Visitar Latinoamerica","Conoce las ciudades mas importantes de America del Sur",200.0))
        challenges.add(Challeng("Visitar Latinoamerica","Conoce las ciudades mas importantes de America del Sur",200.0))

        challengRecycleView.layoutManager = LinearLayoutManager(context);
        challengRecycleView.adapter = ChallengAdapterRecyclerView(challenges,context)
    }

}