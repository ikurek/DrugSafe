package com.ikurek.drugsafe.mydrugs

import android.util.Log
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.database.AppDatabase
import com.ikurek.drugsafe.model.DrugModel
import javax.inject.Inject

class MyDrugsPresenter : MyDrugsContract.Presenter {

    @Inject
    lateinit var appDatabase: AppDatabase

    var view: MyDrugsContract.View? = null
    var drugs: List<DrugModel> = listOf()

    override fun attach(view: MyDrugsContract.View) {
        Log.d("MyDrugsPresenter", "Attached")
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        Log.d("MyDrugsPresenter", "Detached")
        this.view = null
    }

    override fun handleFloatingActionButton() {
        view?.startSearchDrugFragment()
    }

    override fun loadDrugs() {
        drugs = appDatabase.drugDAO().getAll()
        Log.d("MyDrugs", "${drugs.size} drugs saved in Room")

        if (drugs.isEmpty()) {
            view?.clearRecyclerView()
            view?.showBackgroundText()
        } else {
            view?.hideBackgroundText()
            view?.updateRecyclerView(MyDrugsAdapter(drugs) { itemClicked(it) })
        }
    }

    private fun itemClicked(drugModel: DrugModel) {
        view?.startDetailsFragment(drugModel)
    }

}