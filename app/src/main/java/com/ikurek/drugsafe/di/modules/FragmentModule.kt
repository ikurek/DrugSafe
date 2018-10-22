package com.ikurek.drugsafe.di.modules

import com.ikurek.drugsafe.mydrugs.MyDrugsContract
import com.ikurek.drugsafe.mydrugs.MyDrugsPresenter
import com.ikurek.drugsafe.seachdrugs.SearchDrugsContract
import com.ikurek.drugsafe.seachdrugs.SearchDrugsPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideMyDrugsPresenter(): MyDrugsContract.Presenter {
        return MyDrugsPresenter()
    }

    @Provides
    fun provideSearchDrugsFragment(): SearchDrugsContract.Presenter {
        return SearchDrugsPresenter()
    }

}