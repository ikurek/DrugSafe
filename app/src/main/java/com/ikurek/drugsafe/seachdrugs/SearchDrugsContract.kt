package com.ikurek.drugsafe.seachdrugs

import com.ikurek.drugsafe.base.BaseContract

interface SearchDrugsContract {

    interface Presenter : BaseContract.Presenter<SearchDrugsContract.View> {
        fun onItemsLoaded()
        fun onItemClicked()
    }

    interface View: BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun setItems(items: List<String>)
    }
}