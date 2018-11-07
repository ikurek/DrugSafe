package com.ikurek.drugsafe.di.modules

import com.ikurek.drugsafe.drugdetails.DrugDetailsContract
import com.ikurek.drugsafe.drugdetails.DrugDetailsPresenter
import com.ikurek.drugsafe.loginactivity.LoginContract
import com.ikurek.drugsafe.loginactivity.LoginPresenter
import com.ikurek.drugsafe.mainactivity.MainContract
import com.ikurek.drugsafe.mainactivity.MainPresenter
import com.ikurek.drugsafe.mydrugs.MyDrugsContract
import com.ikurek.drugsafe.mydrugs.MyDrugsPresenter
import com.ikurek.drugsafe.registeractivity.RegisterContract
import com.ikurek.drugsafe.registeractivity.RegisterPresenter
import com.ikurek.drugsafe.seachdrugs.SearchDrugsContract
import com.ikurek.drugsafe.seachdrugs.SearchDrugsPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

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

    @Provides
    fun provideMyDrugsPresenter(): MyDrugsContract.Presenter {
        return MyDrugsPresenter()
    }

    @Provides
    fun provideSearchDrugsPresenter(): SearchDrugsContract.Presenter {
        return SearchDrugsPresenter()
    }

    @Provides
    fun provideDrugDetailsPresenter(): DrugDetailsContract.Presenter {
        return DrugDetailsPresenter()
    }

}