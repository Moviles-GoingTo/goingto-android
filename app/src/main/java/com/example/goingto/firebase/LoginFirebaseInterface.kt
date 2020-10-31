package game.ppt.test.oliva.marc.pruebappt.model.repository.data.firebase

import com.google.android.material.textfield.TextInputEditText
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth

interface LoginFirebaseInterface {

    fun loginWithGoogle(signInAccount: GoogleSignInAccount)
    fun loginWithEmail(email:String,password:String)
    fun getFirebaseAuthListener():FirebaseAuth.AuthStateListener?
    fun getCallBackManager():CallbackManager?
    fun getGoogleApiClient():GoogleApiClient?
    fun handleSignInGoogleResult(result: GoogleSignInResult)
}