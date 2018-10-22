package com.ikurek.drugsafe.base

import android.app.Application
import com.ikurek.drugsafe.di.components.ApplicationComponent
import com.ikurek.drugsafe.di.components.DaggerApplicationComponent
import com.ikurek.drugsafe.di.modules.ApplicationModule

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.create().inject(this)
    }

}