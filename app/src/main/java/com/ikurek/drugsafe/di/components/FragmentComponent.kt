package com.ikurek.drugsafe.di.components

import com.ikurek.drugsafe.barcodescanner.BarcodeScannerFragment
import com.ikurek.drugsafe.di.modules.ContextModule
import com.ikurek.drugsafe.di.modules.PresenterModule
import com.ikurek.drugsafe.drugdetails.DrugDetailsFragment
import com.ikurek.drugsafe.mydrugs.MyDrugsFragment
import com.ikurek.drugsafe.replacementslist.ReplacementListFragment
import com.ikurek.drugsafe.savedrug.SaveDrugFragment
import com.ikurek.drugsafe.seachdrugs.SearchDrugsFragment
import dagger.Component

@Component(modules = [PresenterModule::class, ContextModule::class])
interface FragmentComponent {

    fun inject(myDrugsFragment: MyDrugsFragment)
    fun inject(searchDrugsFragment: SearchDrugsFragment)
    fun inject(drugDetailsFragment: DrugDetailsFragment)
    fun inject(replacementListFragment: ReplacementListFragment)
    fun inject(barcodeScannerFragment: BarcodeScannerFragment)
    fun inject(saveDrugFragment: SaveDrugFragment)

}