package com.ikurek.drugsafe.di.components

import com.ikurek.drugsafe.di.modules.ApiModule
import com.ikurek.drugsafe.di.modules.ContextModule
import com.ikurek.drugsafe.loginactivity.LoginPresenter
import com.ikurek.drugsafe.registeractivity.RegisterPresenter
import dagger.Component

@Component(modules = [ContextModule::class, ApiModule::class])
interface PresenterComponent {

    fun inject(loginPresenter: LoginPresenter)
    fun inject(registerPresenter: RegisterPresenter)
}