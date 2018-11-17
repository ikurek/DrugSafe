package com.ikurek.drugsafe.drugdetails

import com.ikurek.drugsafe.base.BaseContract
import com.ikurek.drugsafe.model.DrugModel
import com.leinardi.android.speeddial.SpeedDialView

interface DrugDetailsContract {

    interface Presenter : BaseContract.Presenter<DrugDetailsContract.View>,
        SpeedDialView.OnActionSelectedListener {
        fun getDrugModelFieldMap(): Map<String, String>
        fun isDrugSaved(drugModel: DrugModel): Boolean
    }

    interface View : BaseContract.View {
        fun startSearchReplacementsFragment()
        fun getDrug(): DrugModel
        fun switchFabMenu()
        fun openDrugInBrowser()
    }
}