package com.ikurek.drugsafe.seachdrugs

class SearchDrugsPresenter : SearchDrugsContract.Presenter {

    var view: SearchDrugsContract.View? = null

    override fun attach(view: SearchDrugsContract.View) {
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