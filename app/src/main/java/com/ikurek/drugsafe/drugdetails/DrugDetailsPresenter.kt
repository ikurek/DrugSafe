package com.ikurek.drugsafe.drugdetails

import android.content.Context
import android.util.Log
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.database.AppDatabase
import com.ikurek.drugsafe.model.DrugModel
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import javax.inject.Inject

class DrugDetailsPresenter : DrugDetailsContract.Presenter, SpeedDialView.OnActionSelectedListener {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var appDatabase: AppDatabase

    var view: DrugDetailsContract.View? = null

    override fun attach(view: DrugDetailsContract.View) {
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        this.view = null
    }

    override fun getDrugModelFieldMap(): Map<String, String> {

        val keys = context.resources.getStringArray(R.array.drug_model_fields)
        val values = context.resources.getStringArray(R.array.drug_model_fields_full)

        val map = mutableMapOf<String, String>()

        for (i in keys.indices) {
            map[keys[i]] = values[i]
        }


        return map
    }

    override fun onActionSelected(actionItem: SpeedDialActionItem?): Boolean {
        when (actionItem?.id) {
            R.id.menu_save_drug -> {
                saveDrug()
                view?.switchFabMenu()
            }
            R.id.menu_delete_drug -> {
                deleteDrug()
                view?.switchFabMenu()
            }
            R.id.menu_find_replacements -> {
                view?.startSearchReplacementsFragment()
            }
            R.id.menu_open_in_browser -> {
                view?.openDrugInBrowser()
            }
        }

        // False closes menu
        return false
    }

    override fun isDrugSaved(drugModel: DrugModel): Boolean {
        return appDatabase.drugDAO().getAll().contains(drugModel)
    }

    private fun saveDrug() {
        view?.getDrug().let { drug ->
            appDatabase.drugDAO().save(drug!!)
            Log.d("DrugDetails", "Saving ${drug.name}")
        }
    }

    private fun deleteDrug() {
        view?.getDrug().let { drug ->
            appDatabase.drugDAO().delete(drug!!)
            Log.d("DrugDetails", "Removing ${drug.name}")
        }
    }

}