package com.ikurek.drugsafe.di.modules

import com.ikurek.drugsafe.loginactivity.LoginContract
import com.ikurek.drugsafe.loginactivity.LoginPresenter
import com.ikurek.drugsafe.mainactivity.MainContract
import com.ikurek.drugsafe.mainactivity.MainPresenter
import com.ikurek.drugsafe.registeractivity.RegisterContract
import com.ikurek.drugsafe.registeractivity.RegisterPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideMainPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    @Provides
    fun provideLoginPresenter(): LoginContract.Presenter {
        return LoginPresenter()
    }

    @Provides
    fun provideRegisterPresenter(): RegisterContract.Presenter {
        return RegisterPresenter()
    }

}