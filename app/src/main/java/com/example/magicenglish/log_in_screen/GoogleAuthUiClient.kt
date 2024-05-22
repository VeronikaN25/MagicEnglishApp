package com.example.magicenglish.log_in_screen

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.magicenglish.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient //клиент для входа с помощью Google Tap Sign in
) {
    //обьект Firebase auth
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        val result = try {
            //beginSignIn начало процесса входа
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            null
        }
        // если не нул то возврвшает intentSender для продолжения процесса входа
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        // получение данных для входа из Intent
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        // Токент гугл id
        val googleIdToken = credential.googleIdToken
        // создание учетных данных для входа
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            //выполняет аутентификацию в Firebase и получает данные пользователя.
            val user = auth.signInWithCredential(googleCredentials).await().user
            // объект, содержащий данные пользователя или сообщение об ошибке.
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut() {
        try {
            //выходит из учетной записи Google One Tap.
            oneTapClient.signOut().await()
            //выходит из учетной записи Firebase.
            auth.signOut()
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }

    //функция для получения данных текущего авторизованного пользователя.
    // auth.currentUser текущий авторизованный пользователь Firebase.
    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    //создает и возвращает запрос для входа.
    private fun buildSignInRequest(): BeginSignInRequest {
        // строитель для создания запроса.
        return BeginSignInRequest.Builder()
            //устанавливает опции для запроса Google ID Token.
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    // получает идентификатор клиента сервера из строковых ресурсов.
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            //автоматически выбирает учетную запись, если возможно.
            .setAutoSelectEnabled(true)
            .build()
    }
}