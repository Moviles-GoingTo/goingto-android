package com.example.goingto.controller.activities

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.goingto.R
import com.example.goingto.ui.challenges.ChallengesFragment
import com.example.goingto.ui.home.HomeFragment
import com.example.goingto.ui.points.PointsFragment
import com.example.goingto.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var active: Fragment
    private lateinit var homeFragment: Fragment
    private lateinit var pointsFragment: Fragment
    private lateinit var challengsFragment: Fragment
    private lateinit var profileFragment: Fragment
    private val fm = supportFragmentManager
   /* private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    main_toolbar.isVisible = false
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_points -> {
                    main_toolbar.isVisible = true

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_challenges -> {
                    main_toolbar.isVisible = true


                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    main_toolbar.isVisible = true

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_points,
                R.id.navigation_challenges,
                R.id.navigation_profile
            )
        )
        //supportActionBar?.hide()
        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)

        //setUpFragments()
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setUpFragments() {
        homeFragment = HomeFragment()
        pointsFragment = PointsFragment()
        challengsFragment = ChallengesFragment()
        profileFragment = ProfileFragment()
        //fragment que se vera primero
        active = HomeFragment()

        fm.beginTransaction().add(R.id.nav_host_fragment, profileFragment, "4")
            .hide(profileFragment)
            .commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, challengsFragment, "3")
            .hide(challengsFragment).commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, pointsFragment, "2").hide(pointsFragment)
            .commit()
        fm.beginTransaction().add(R.id.nav_host_fragment, homeFragment, "1").commit()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> findNavController(R.id.nav_host_fragment).popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}