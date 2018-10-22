package com.ikurek.drugsafe.mydrugs

import com.ikurek.drugsafe.base.BaseContract

interface MyDrugsContract {

    interface Presenter : BaseContract.Presenter<MyDrugsContract.View> {
        fun onItemsLoaded()
        fun onItemClicked()
    }

    interface View: BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun setItems(items: List<String>)
    }
}