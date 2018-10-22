package com.ikurek.drugsafe.di.modules

import com.ikurek.drugsafe.mainactivity.MainContract
import com.ikurek.drugsafe.mainactivity.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideMainPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}