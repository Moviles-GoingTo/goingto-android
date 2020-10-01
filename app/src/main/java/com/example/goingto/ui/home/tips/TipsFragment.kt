package com.example.goingto.ui.home.tips

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goingto.R
import com.example.goingto.adapter.TipAdapterRecyclerView
import com.example.goingto.model.Tip
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
        tips.add(Tip(resources.getString(R.string.lorem_ipsum)))
        tips.add(Tip(resources.getString(R.string.lorem_ipsum)))
        tips.add(Tip(resources.getString(R.string.lorem_ipsum)))
        tips.add(Tip(resources.getString(R.string.lorem_ipsum)))
        tips.add(Tip(resources.getString(R.string.lorem_ipsum)))
        val tipAdapter = TipAdapterRecyclerView(tips)
        tips_recyclerview.adapter = tipAdapter

    }

}