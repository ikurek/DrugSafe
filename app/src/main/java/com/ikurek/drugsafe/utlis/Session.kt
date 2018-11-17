package com.ikurek.drugsafe.utlis

import android.content.Context
import android.content.Intent
import androidx.room.Room
import com.ikurek.drugsafe.database.AppDatabase
import com.ikurek.drugsafe.loginactivity.LoginActivity

object Session {

    fun signOut(context: Context) {
        // Wipe data
        clearSharedPreferences(context)
        clearDatabase(context)
        // Prepare login activity
        val intent = Intent(context, LoginActivity::class.java)
        // Start login activity
        context.startActivity(intent)
    }

    private fun clearSharedPreferences(context: Context) {
        // Get instance of SharedPrefs
        val sharedPreferences = context.getSharedPreferences("drugsafesp", Context.MODE_PRIVATE)
        // Remove all saved entries
        sharedPreferences.edit().clear().apply()
    }

    private fun clearDatabase(context: Context) {
        // Build instance of database
        val database = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "drugsafedb"
        )
            .allowMainThreadQueries() //BAD DEVELOPER!!!
            .fallbackToDestructiveMigration() //VERY BAD DEVELOPER
            .build()

        // Remove content from tables
        database.clearAllTables()
    }
}