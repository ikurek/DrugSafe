package com.ikurek.drugsafe.drugdetails

import android.content.Context
import com.ikurek.drugsafe.base.BaseApp
import javax.inject.Inject

class DrugDetailsPresenter : DrugDetailsContract.Presenter {


    @Inject
    lateinit var context: Context

    var view: DrugDetailsContract.View? = null

    override fun attach(view: DrugDetailsContract.View) {
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        this.view = null
    }

}