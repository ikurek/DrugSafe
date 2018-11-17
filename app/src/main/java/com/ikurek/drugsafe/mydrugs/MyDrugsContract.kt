package com.ikurek.drugsafe.mydrugs

import com.ikurek.drugsafe.base.BaseContract
import com.ikurek.drugsafe.model.DrugModel

interface MyDrugsContract {

    interface Presenter : BaseContract.Presenter<MyDrugsContract.View> {
        fun handleFloatingActionButton()
        fun loadDrugs()
    }

    interface View : BaseContract.View {
        fun startSearchDrugFragment()
        fun updateRecyclerView(adapter: MyDrugsAdapter)
        fun clearRecyclerView()
        fun showBackgroundText()
        fun hideBackgroundText()
        fun startDetailsFragment(drugModel: DrugModel)
    }
}