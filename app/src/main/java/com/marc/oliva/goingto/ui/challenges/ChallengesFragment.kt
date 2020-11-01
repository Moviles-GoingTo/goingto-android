package com.marc.oliva.goingto.ui.challenges

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goingto.ui.challenges.ChallengesViewModel
import com.marc.oliva.goingto.R

class ChallengesFragment : Fragment() {

    companion object {
        fun newInstance() = ChallengesFragment()
    }

    private lateinit var viewModel: ChallengesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.challenges_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChallengesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}