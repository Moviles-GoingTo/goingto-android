package com.example.goingto.controller.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.goingto.R
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import game.ppt.test.oliva.marc.pruebappt.model.repository.data.firebase.LoginFirebaseImpl
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.profile_fragment.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private var mLogin: LoginFirebaseImpl? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mLogin = LoginFirebaseImpl(FirebaseAuth.getInstance(), this)
        try {
            val info =
                packageManager.getPackageInfo("com.example.goingto", PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
        ui()
    }

    private fun ui() {
        login_button.setOnClickListener {
            val email = etLoginEmail.text.toString()
            val password = etLoginPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                mLogin?.loginWithEmail(email,password)
            }

        }
        tvRegister.setOnClickListener {
            val intento = Intent(this, RegisterActivity::class.java)
            startActivity(intento)
        }
        btFacebook.setOnClickListener {
            //login facebook
            val action = LoginButton(this)
            action.performClick()
        }
        btGoogle.setOnClickListener {
            //login google
            val intent = Auth.GoogleSignInApi.getSignInIntent(mLogin!!.getGoogleApiClient())
            startActivityForResult(intent, 777)
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }
    override fun onStart() {
        super.onStart()
        mLogin?.getFirebaseAuthListener()
            ?.let { FirebaseAuth.getInstance().addAuthStateListener(it) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 777) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result != null) {
                mLogin?.handleSignInGoogleResult(result)
            }
        }

        mLogin?.getCallBackManager()!!.onActivityResult(requestCode, resultCode, data)

    }

    override fun onStop() {
        super.onStop()
        if (mLogin?.getFirebaseAuthListener() != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mLogin?.getFirebaseAuthListener()!!)
        }
    }
}