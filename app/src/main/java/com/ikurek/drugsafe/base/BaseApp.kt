package com.ikurek.drugsafe.base

import android.app.Application
import android.util.Log
import com.ikurek.drugsafe.api.ApiInterface
import com.ikurek.drugsafe.di.components.*
import com.ikurek.drugsafe.di.modules.ActivityModule
import com.ikurek.drugsafe.di.modules.ApiModule
import com.ikurek.drugsafe.di.modules.ContextModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class BaseApp : Application() {


    companion object {
        lateinit var presenterComponent: PresenterComponent
        lateinit var activityComponent: ActivityComponent
        lateinit var apiComponent: ApiComponent

        fun getDaggerPresenterComponent() = presenterComponent
        fun getDaggerActivityComponent() = activityComponent
        fun getDaggerApiComponent() = apiComponent

    }

    lateinit var api: ApiInterface

    override fun onCreate() {
        super.onCreate()
        Log.e("BaseApp", "Running")
        buildRetrofit()
        buildDagger()
    }

    private fun buildDagger() {
        presenterComponent = DaggerPresenterComponent.builder()
                .contextModule(ContextModule(applicationContext))
                .apiModule(ApiModule(api))
                .build()
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule())
                .build()
        apiComponent = DaggerApiComponent.builder()
                .apiModule(ApiModule(api))
                .contextModule(ContextModule(applicationContext))
                .build()
    }

    private fun buildRetrofit() {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://igor-server.ikurek.p5.tiktalik.io:8080/api/v1/")
                .build()

        api = retrofit.create(ApiInterface::class.java)
    }



}