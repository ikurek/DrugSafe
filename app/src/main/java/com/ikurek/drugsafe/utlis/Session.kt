package com.ikurek.drugsafe.utlis

import android.content.Context
import android.content.Intent
import com.ikurek.drugsafe.loginactivity.LoginActivity

object Session {

    fun signOut(context: Context) {
        // Get instance of SharedPrefs
        val sharedPreferences = context.getSharedPreferences("drugsafesp", Context.MODE_PRIVATE)
        // Remove all saved entries
        sharedPreferences.edit().clear().apply()
        // Prepare login activity
        val intent = Intent(context, LoginActivity::class.java)
        // Start login activity
        context.startActivity(intent)
    }
}