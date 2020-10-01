package com.example.goingto.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.goingto.ui.home.description.DescriptionFragment
import com.example.goingto.ui.home.reviews.ReviewsFragment
import com.example.goingto.ui.home.tips.TipsFragment

class OptionsPlaceAdapterViewPager(fm: FragmentManager?, context: Context?) :
    FragmentPagerAdapter(fm!!) {
    private val tabTitles = arrayOf("Descripcion", "Reseñas", "Tips")
    override fun getItem(i: Int): Fragment {
        return when (i) {
            0 -> DescriptionFragment()
            1 -> ReviewsFragment()
            2 -> TipsFragment()
            else -> DescriptionFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    init {
        val mContext = context
    }
}
