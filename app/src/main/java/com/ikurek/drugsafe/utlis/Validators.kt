package com.ikurek.drugsafe.utlis

import android.util.Patterns

object Validators {

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordLongerThenSixChars(password: String): Boolean {
        return password.length > 6
    }

    fun isPasswordMatching(password: String, repeatPassword: String): Boolean {
        return password.equals(repeatPassword)
    }

}