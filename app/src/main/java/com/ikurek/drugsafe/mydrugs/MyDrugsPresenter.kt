package com.ikurek.drugsafe.mydrugs

import android.content.Context
import javax.inject.Inject

class MyDrugsPresenter : MyDrugsContract.Presenter {

    var view: MyDrugsContract.View? = null

    override fun attach(view: MyDrugsContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun onItemsLoaded() {

    }

    override fun onItemClicked() {
    }

}