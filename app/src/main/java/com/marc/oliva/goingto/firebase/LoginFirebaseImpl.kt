package com.marc.oliva.goingto.firebase

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.marc.oliva.goingto.controller.activities.LoginActivity

import com.marc.oliva.goingto.controller.activities.MainActivity
import com.example.goingto.model.UserLogin
import com.example.goingto.model.UserResponse
import com.example.goingto.network.ReferenceService
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.marc.oliva.goingto.R
import game.ppt.test.oliva.marc.pruebappt.model.repository.data.firebase.LoginFirebaseInterface
import kotlinx.android.synthetic.main.loading_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFirebaseImpl(firebaseAuth: FirebaseAuth, activity: LoginActivity) :
    LoginFirebaseInterface, GoogleApiClient.OnConnectionFailedListener {

    val SIGN_IN_CODE = 777

    var firebaseAuth: FirebaseAuth? = firebaseAuth
    private var firebaseAuthListener: FirebaseAuth.AuthStateListener? = null
    private val mActivity = activity

    private var dialog: Dialog? = null
    private var googleApiClient: GoogleApiClient? = null

    private var callBackManager: CallbackManager? = null

    init {
        dialog = Dialog(mActivity)
        firebaseAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                goMainScreen()
            }
        }

        //GOOGLE LOGIN
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(mActivity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleApiClient = GoogleApiClient.Builder(mActivity)
            .enableAutoManage(mActivity, mActivity)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

        //FACEBOOK LOGIN
        callBackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callBackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                handleFacebookAcessToken(result!!.accessToken)
            }

            override fun onCancel() {
                Toast.makeText(
                    mActivity.applicationContext,
                    "Se cancelo el inicio de sesion",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onError(error: FacebookException?) {
                Log.i("ERROR", error.toString())
                Toast.makeText(
                    mActivity.applicationContext,
                    "Ocurrio un error al iniciar sesion",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    override fun handleSignInGoogleResult(result: GoogleSignInResult) {
        Log.i("ERROR", result.isSuccess.toString())
        if (result.isSuccess) {
            loginWithGoogle(result.signInAccount!!)
        } else {
            Toast.makeText(mActivity.applicationContext, R.string.not_log_in, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun handleFacebookAcessToken(accessToken: AccessToken) {
        //showLoadingLogin(true)
        val credential = FacebookAuthProvider.getCredential(accessToken.token)
        firebaseAuth!!.signInWithCredential(credential).addOnCompleteListener {
            if (!it.isSuccessful) {
                Toast.makeText(
                    mActivity.applicationContext,
                    R.string.not_firebase_auth,
                    Toast.LENGTH_SHORT
                ).show()
                //showLoadingLogin(false)
            }

        }
    }

    private fun goMainScreen() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        //isThereAccount(currentUser) // Verificamos is existe la cuenta
        val intent = Intent(mActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        mActivity.startActivity(intent)
    }
    private fun showLoadingLogin(visibility: Boolean) {
        if (visibility) {
            //dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setCancelable(false)
            dialog!!.setContentView(R.layout.loading_dialog)
            dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))// PERMITE UN DIALOGO TRANSPARENTE PARA QUE SE NOTE LOS CORNERS
            //dialog!!.window!!.attributes.windowAnimations = R.style.DialogScaleAnimation

            //dialog!!.description_loading_textview.text = "Iniciando Sesion..."
            dialog!!.loading_login_layout.visibility = View.VISIBLE
            dialog!!.loading_login_imageview.setBackgroundResource(R.drawable.logo_white)
            //val frameAnimation = dialog!!.loading_login_imageview.background as AnimationDrawable
            //frameAnimation.start()

            dialog!!.show()
        } else dialog!!.dismiss()

    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun loginWithEmail(email: String, password: String) {
        showLoadingLogin(true)
        val apiAdapter = ReferenceService()
        val apiService = apiAdapter.getClientService()
        val call = apiService.authenticate(UserLogin(email,password))
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    firebaseAuth!!.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            mActivity
                        ) {
                            if (it.isSuccessful) {
                                showLoadingLogin(false)
                                goMainScreen()
                            } else {
                                firebaseAuth!!.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(mActivity) { it ->
                                        if (it.isSuccessful) {

                                            firebaseAuth!!.signInWithEmailAndPassword(
                                                email,
                                                password
                                            )
                                            showLoadingLogin(false)

                                            goMainScreen()
                                        } else {
                                            showLoadingLogin(false)

                                            Toast.makeText(
                                                mActivity,
                                                "Ha Ocurrido un Error!.Intentelo nuevamente.",
                                                Toast.LENGTH_SHORT
                                            )
                                                .show()
                                        }
                                    }
                            }
                        }

                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                showLoadingLogin(false)
                Toast.makeText(
                    mActivity,
                    "Ha ocurrido un error al iniciar sesion.",
                    Toast.LENGTH_LONG
                ).show()
                t.stackTrace
            }

        })


    }
    override fun loginWithGoogle(signInAccount: GoogleSignInAccount) {
        showLoadingLogin(true)
        val credential = GoogleAuthProvider.getCredential(signInAccount.idToken, null)
        firebaseAuth!!.signInWithCredential(credential).addOnCompleteListener(mActivity) {
            if (!it.isSuccessful) {
                Toast.makeText(
                    mActivity.applicationContext,
                    R.string.not_firebase_auth,
                    Toast.LENGTH_SHORT
                ).show()
            }
            showLoadingLogin(false)
        }


    }


    override fun getFirebaseAuthListener(): FirebaseAuth.AuthStateListener? {
        return firebaseAuthListener
    }

    override fun getCallBackManager(): CallbackManager? {
        return callBackManager
    }

    override fun getGoogleApiClient(): GoogleApiClient? {
        return googleApiClient
    }


}