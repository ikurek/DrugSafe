package com.ikurek.drugsafe.di.components

import com.ikurek.drugsafe.di.modules.ContextModule
import com.ikurek.drugsafe.di.modules.PresenterModule
import com.ikurek.drugsafe.drugdetails.DrugDetailsFragment
import com.ikurek.drugsafe.mydrugs.MyDrugsFragment
import com.ikurek.drugsafe.seachdrugs.SearchDrugsFragment
import dagger.Component

@Component(modules = [PresenterModule::class, ContextModule::class])
interface FragmentComponent {

    fun inject(myDrugsFragment: MyDrugsFragment)
    fun inject(searchDrugsFragment: SearchDrugsFragment)
    fun inject(drugDetailsFragment: DrugDetailsFragment)

}