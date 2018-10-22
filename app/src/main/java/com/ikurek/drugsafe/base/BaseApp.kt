package com.ikurek.drugsafe.base

import android.app.Application
import com.ikurek.drugsafe.di.components.DaggerApplicationComponent

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.create().inject(this)
    }

}