package com.example.magicenglish

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.magicenglish.dictionary.presentation.DictionaryUI
import com.example.magicenglish.home_screen_.HomeScreen
import com.example.magicenglish.home_screen_.ProfileScreen
import com.example.magicenglish.log_in_screen.GoogleAuthUiClient
import com.example.magicenglish.log_in_screen.LogInScreen
import com.example.magicenglish.log_in_screen.SignInViewModel
import com.example.magicenglish.test_trainer.screen.QuestionsViewModel
import com.example.magicenglish.test_trainer.screen.QuizHome
import com.example.magicenglish.test_trainer.screen.Screens
import com.example.magicenglish.test_trainer.screen.TestCompleted
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        const val RC_SIGN_IN = 100
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // firebase auth instance
        mAuth = FirebaseAuth.getInstance()

        // configure Google SignIn
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            if (mAuth.currentUser == null) {
                LogInScreen {
                    signIn()
                }
            } else {
                val user: FirebaseUser = mAuth.currentUser!!
                ProfileScreen(
                    profileImage = user.photoUrl!!,
                    name = user.displayName!!,
                    signOutClicked = {
                        signOut()
                    }
                )
            }
//            val viewModel = viewModel<QuestionsViewModel>()
//            val navController = rememberNavController()
//            NavHost(
//                navController = navController,
//                startDestination = Screens.QuizHome.name
//            ) {
//                composable(Screens.QuizHome.name) {
//                    QuizHome(
//                        navController,
//                        viewModel
//                    )
//                }
//                composable(
//                    Screens.QuizEnd.name + "/{correctAnswer}/{totalQuestion}",
//                    arguments = listOf(
//                        navArgument("correctAnswer") {
//                            type = NavType.IntType
//                            defaultValue = 0 },
//                        navArgument("totalQuestion") {
//                            type = NavType.IntType
//                            defaultValue = 0
//                        }
//                    )
//                ) { backStackEntry ->
//                    TestCompleted(
//                        navController = navController,
//                        backStackEntry.arguments?.getInt("correctAnswer") ?: 0,
//                        backStackEntry.arguments?.getInt("totalQuestion") ?: 0
//                    )
//                }
//            }
        }
    }
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // result returned from launching the intent from GoogleSignInApi.getSignInIntent()
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google SignIn was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: Exception) {
                    // Google SignIn Failed
                    Log.d("SignIn", "Google SignIn Failed")
                }
            } else {
                Log.d("SignIn", exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // SignIn Successful
                    Toast.makeText(this, "SignIn Successful", Toast.LENGTH_SHORT).show()
                    setContent {
                        val user: FirebaseUser = mAuth.currentUser!!
                        ProfileScreen(
                            profileImage = user.photoUrl!!,
                            name = user.displayName!!,
                            signOutClicked = {
                                signOut()
                            }
                        )
                    }
                } else {
                    // SignIn Failed
                    Toast.makeText(this, "SignIn Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signOut() {
        // get the google account
        val googleSignInClient: GoogleSignInClient

        // configure Google SignIn
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Sign Out of all accounts
        mAuth.signOut()
        googleSignInClient.signOut()
            .addOnSuccessListener {
                Toast.makeText(this, "Sign Out Successful", Toast.LENGTH_SHORT).show()
                setContent {
                    LogInScreen {
                        signIn()
                    }

                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Sign Out Failed", Toast.LENGTH_SHORT).show()
            }
    }
}
