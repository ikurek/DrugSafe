package com.ikurek.drugsafe.di.modules

import android.app.Application
import android.content.Context
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.base.BaseScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides dependency for application
 */

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @BaseScope
    fun provideApplication(): Application {
        return baseApp
    }

    @Provides
    @Singleton
    @BaseScope
    fun provideContext(): Context {
        return baseApp
    }
}