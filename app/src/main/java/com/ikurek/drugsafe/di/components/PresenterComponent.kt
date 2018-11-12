package com.ikurek.drugsafe.di.components

import com.ikurek.drugsafe.di.modules.ApiModule
import com.ikurek.drugsafe.di.modules.ContextModule
import com.ikurek.drugsafe.di.modules.SharedPreferencesModule
import com.ikurek.drugsafe.drugdetails.DrugDetailsPresenter
import com.ikurek.drugsafe.loginactivity.LoginPresenter
import com.ikurek.drugsafe.mainactivity.MainPresenter
import com.ikurek.drugsafe.mydrugs.MyDrugsPresenter
import com.ikurek.drugsafe.registeractivity.RegisterPresenter
import com.ikurek.drugsafe.replacementslist.ReplacementListPresenter
import com.ikurek.drugsafe.seachdrugs.SearchDrugsPresenter
import dagger.Component

@Component(modules = [ContextModule::class, ApiModule::class, SharedPreferencesModule::class])
interface PresenterComponent {

    fun inject(loginPresenter: LoginPresenter)
    fun inject(registerPresenter: RegisterPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(myDrugsPresenter: MyDrugsPresenter)
    fun inject(searchDrugsPresenter: SearchDrugsPresenter)
    fun inject(drugDetailsPresenter: DrugDetailsPresenter)
    fun inject(replacementListPresenter: ReplacementListPresenter)
}