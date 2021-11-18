package com.example.keepclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.lang.Exception


class GoogleSignInActivity : AppCompatActivity() {

    private lateinit var googleSignInClient : GoogleSignInClient;
    private lateinit var firebaseAuth: FirebaseAuth;

    private companion object{
        private const val RC_SIGN_IN = 9001;
        private const val TAG = "GoogleActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_sign_in)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        //configure Google SignIn
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()

        googleSignInClient= GoogleSignIn.getClient(this,googleSignInOptions)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        // Google SignIn Button
        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setOnClickListener{
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "onCreate: begin Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)

        }

    }

    private fun checkUser(){
        //If user is signed in
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode== RC_SIGN_IN){
            Log.d(TAG, "onActivityResult: Google SignIn intent result")
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)

            try{
                val account = accountTask.getResult(ApiException::class.java)
                firebaseAuthWithGoogleAccount(account)

            }catch (e: Exception){
                Log.d(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun firebaseAuthWithGoogleAccount(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogleAccount: Begin Firebase auth with google")
        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { authResult ->
                Log.d(TAG, "firebaseAuthWithGoogleAccount: LoggedIn")

                val firebaseUser = firebaseAuth.currentUser

                val uid = firebaseUser!!.uid
                val email = firebaseUser!!.email


                Log.d(TAG, "firebaseAuthWithGoogleAccount: UID: $uid")
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Email: $email")

                //Check user status: new or existing
                if (authResult.additionalUserInfo!!.isNewUser){
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Account Created")
                    Toast.makeText(this, "New Account Created", Toast.LENGTH_SHORT).show()

                }else{
                    // Existing user
                    Log.d(TAG, "firebaseAuthWithGoogleAccount: Existing User")
                    Toast.makeText(this, "$email ...Logged In", Toast.LENGTH_SHORT).show()
                }

                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener{ e ->
                Log.d(TAG, "firebaseAuthWithGoogleAccount: Logged in failed due to ${e.message}")
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }

    }
}