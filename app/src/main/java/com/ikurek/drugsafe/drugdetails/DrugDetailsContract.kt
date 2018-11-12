package com.ikurek.drugsafe.drugdetails

import com.ikurek.drugsafe.base.BaseContract
import com.leinardi.android.speeddial.SpeedDialView

interface DrugDetailsContract {

    interface Presenter : BaseContract.Presenter<DrugDetailsContract.View>,
        SpeedDialView.OnActionSelectedListener {
        fun getDrugModelFieldMap(): Map<String, String>
    }

    interface View : BaseContract.View {
        fun startSearchReplacementsFragment()
    }
}