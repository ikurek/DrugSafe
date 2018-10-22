package com.ikurek.drugsafe.di.components

import com.ikurek.drugsafe.di.modules.FragmentModule
import com.ikurek.drugsafe.mydrugs.MyDrugsContract
import com.ikurek.drugsafe.mydrugs.MyDrugsFragment
import com.ikurek.drugsafe.seachdrugs.SearchDrugsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(myDrugsFragment: MyDrugsFragment)
    fun inject(searchDrugsFragment: SearchDrugsFragment)

}