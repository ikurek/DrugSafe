package com.ikurek.drugsafe.di.components

import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.di.modules.ApplicationModule
import com.ikurek.drugsafe.loginactivity.LoginContract
import dagger.Component

/**
 * Dagger component for Application
 * Allows injecting BaseApp
 */
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApp)
    fun inject(presenter: LoginContract.Presenter)

}