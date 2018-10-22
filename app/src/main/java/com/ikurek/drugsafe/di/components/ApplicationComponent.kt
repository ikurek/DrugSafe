package com.ikurek.drugsafe.di.components

import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.di.modules.ApplicationModule
import dagger.Component

/**
 * Dagger component for Application
 * Allows injecting BaseApp
 */
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApp)

}