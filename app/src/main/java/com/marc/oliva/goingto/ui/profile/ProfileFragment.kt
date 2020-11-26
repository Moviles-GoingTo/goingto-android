package com.marc.oliva.goingto.ui.profile

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goingto.ui.profile.ProfileViewModel
import com.marc.oliva.goingto.R
import com.marc.oliva.goingto.controller.activities.LoginActivity
import com.marc.oliva.goingto.controller.activities.MainActivity
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val namePref: String? = sharedPreferences?.getString("name", "Miguelito")
        val surnamePref: String? = sharedPreferences?.getString("surname", "Ugarte")
        val birthdatePref: String? = sharedPreferences?.getString("birthdate", "27/01/1999")

        val completeName = namePref + ' ' + surnamePref
        tvProfileName.text = completeName
        tvBirthdate.text = birthdatePref

        button_close_sesion.setOnClickListener {
            val inter = activity as MainActivity
            inter.showLogOutConfirmationDialog()
        }
    }
    interface LogoutMethod {
        fun showLogOutConfirmationDialog()
    }
}