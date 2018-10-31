package com.ikurek.drugsafe.di.modules

import android.app.Application
import android.content.Context
import com.ikurek.drugsafe.base.BaseApp
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
    fun provideApplication(): Application {
        return baseApp
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return baseApp
    }
}