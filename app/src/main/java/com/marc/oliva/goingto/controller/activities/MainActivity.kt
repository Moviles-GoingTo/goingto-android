package com.marc.oliva.goingto.controller.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.marc.oliva.goingto.ui.challenges.ChallengesFragment
import com.marc.oliva.goingto.ui.home.HomeFragment
import com.marc.oliva.goingto.ui.points.PointsFragment
import com.marc.oliva.goingto.ui.profile.ProfileFragment
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.marc.oliva.goingto.R

class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener,
    ProfileFragment.LogoutMethod  {

    private lateinit var active: Fragment
    private lateinit var homeFragment: Fragment
    private lateinit var pointsFragment: Fragment
    private lateinit var challengsFragment: Fragment
    private lateinit var profileFragment: Fragment
    private val fm = supportFragmentManager
    private var googleApiClient: GoogleApiClient? = null

    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseAuthListener: FirebaseAuth.AuthStateListener? = null
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
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                //setUserData(user)
            } else {
                goLogInScreen()
            }
        }
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
    private fun logOut() {
        firebaseAuth!!.signOut()
        LoginManager.getInstance().logOut()
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback {
            if (it.isSuccess) {
                goLogInScreen()
            } else {
                Toast.makeText(
                    applicationContext,
                    R.string.not_close_session, Toast.LENGTH_SHORT
                ).show()
            }
        }
        goLogInScreen()

    }


    private fun goLogInScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    override fun showLogOutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Cerrar Sesion?")
        builder.setPositiveButton("Si") { dialog, which ->
            logOut()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog?.dismiss()
        }
        // Create and show the AlertDialog
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }
}