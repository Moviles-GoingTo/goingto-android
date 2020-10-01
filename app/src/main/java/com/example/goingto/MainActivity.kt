package com.example.goingto

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {

                    fm.beginTransaction().hide(active).show(homeFragment).commit()
                    active = homeFragment
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_points -> {

                    fm.beginTransaction().hide(active).show(pointsFragment).commit()
                    active = pointsFragment
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_challenges -> {

                    fm.beginTransaction().hide(active).show(challengsFragment).commit()
                    active = challengsFragment
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {

                    fm.beginTransaction().hide(active).show(profileFragment).commit()
                    active = profileFragment
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_points, R.id.navigation_challenges,
                R.id.navigation_profile
            )
        )
        supportActionBar?.hide()
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        setUpFragments()
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setUpFragments() {
        homeFragment = HomeFragment()
        pointsFragment = PointsFragment()
        challengsFragment = ChallengesFragment()
        profileFragment = ProfileFragment()
        //fragment que se vera primero
        active = HomeFragment()

        fm.beginTransaction().add(R.id.container, profileFragment, "4").hide(profileFragment)
            .commit()
        fm.beginTransaction().add(R.id.container, challengsFragment, "3")
            .hide(challengsFragment).commit()
        fm.beginTransaction().add(R.id.container, pointsFragment, "2").hide(pointsFragment)
            .commit()
        fm.beginTransaction().add(R.id.container, homeFragment, "1").commit()

    }
}