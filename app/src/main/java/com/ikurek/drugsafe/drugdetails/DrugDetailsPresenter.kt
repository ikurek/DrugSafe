package com.ikurek.drugsafe.drugdetails

import android.content.Context
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import javax.inject.Inject

class DrugDetailsPresenter : DrugDetailsContract.Presenter, SpeedDialView.OnActionSelectedListener {

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
                //TODO: Not Implemented
            }
            R.id.menu_find_replacements -> {
                view?.startSearchReplacementsFragment()
            }
        }

        // False closes menu
        return false
    }

}