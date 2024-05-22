package com.example.magicenglish.log_in_screen

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)