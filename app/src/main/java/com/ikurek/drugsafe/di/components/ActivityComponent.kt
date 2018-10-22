package com.ikurek.drugsafe.di.components

import com.ikurek.drugsafe.di.modules.ActivityModule
import com.ikurek.drugsafe.mainactivity.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger component for Application
 * Allows injecting BaseApp
 */
@Singleton
@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}