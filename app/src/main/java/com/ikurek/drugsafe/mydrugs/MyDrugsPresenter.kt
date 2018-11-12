package com.ikurek.drugsafe.mydrugs

import android.util.Log

class MyDrugsPresenter : MyDrugsContract.Presenter {

    var view: MyDrugsContract.View? = null

    override fun attach(view: MyDrugsContract.View) {
        Log.d("MyDrugsPresenter", "Attached")

        this.view = view
    }

    override fun detach() {
        Log.d("MyDrugsPresenter", "Detached")
        this.view = null
    }

    override fun handleFloatingActionButton() {
        view?.startSearchDrugFragment()
    }

}