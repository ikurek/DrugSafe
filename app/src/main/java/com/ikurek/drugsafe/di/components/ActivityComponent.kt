package com.ikurek.drugsafe.di.components

import com.ikurek.drugsafe.di.modules.ActivityModule
import com.ikurek.drugsafe.di.modules.ContextModule
import com.ikurek.drugsafe.loginactivity.LoginActivity
import com.ikurek.drugsafe.mainactivity.MainActivity
import com.ikurek.drugsafe.registeractivity.RegisterActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger component for Application
 * Allows injecting BaseApp
 */
@Singleton
@Component(modules = [ActivityModule::class, ContextModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(registerActivity: RegisterActivity)

}