package com.ikurek.drugsafe.base

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.ikurek.drugsafe.api.DrugsApi
import com.ikurek.drugsafe.api.UsersApi
import com.ikurek.drugsafe.di.components.*
import com.ikurek.drugsafe.di.modules.ApiModule
import com.ikurek.drugsafe.di.modules.ContextModule
import com.ikurek.drugsafe.di.modules.PresenterModule
import com.ikurek.drugsafe.di.modules.SharedPreferencesModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BaseApp : Application() {


    companion object {
        lateinit var presenterComponent: PresenterComponent
        lateinit var activityComponent: ActivityComponent
        lateinit var fragmentComponent: FragmentComponent
    }

    lateinit var usersApi: UsersApi
    lateinit var drugsApi: DrugsApi
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate() {
        super.onCreate()
        Log.e("BaseApp", "Running")
        buildRetrofit()
        buildSharedPreferences()
        buildDagger()
    }

    private fun buildDagger() {
        presenterComponent = DaggerPresenterComponent.builder()
                .contextModule(ContextModule(applicationContext))
            .apiModule(ApiModule(usersApi, drugsApi))
            .sharedPreferencesModule(SharedPreferencesModule(sharedPreferences))
                .build()
        activityComponent = DaggerActivityComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .presenterModule(PresenterModule())
            .sharedPreferencesModule(SharedPreferencesModule(sharedPreferences))
                .build()
        fragmentComponent = DaggerFragmentComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .presenterModule(PresenterModule())
            .build()

    }

    private fun buildRetrofit() {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://ikurek.pl:8444")
                .build()

        usersApi = retrofit.create(UsersApi::class.java)
        drugsApi = retrofit.create(DrugsApi::class.java)
    }

    private fun buildSharedPreferences() {
        sharedPreferences = applicationContext.getSharedPreferences("drugsafesp", Context.MODE_PRIVATE)
    }



}