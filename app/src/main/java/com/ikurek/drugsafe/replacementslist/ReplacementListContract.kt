package com.ikurek.drugsafe.replacementslist

import android.os.IBinder
import com.ikurek.drugsafe.base.BaseContract
import com.ikurek.drugsafe.model.DrugModel

interface ReplacementListContract {

    interface Presenter : BaseContract.Presenter<ReplacementListContract.View> {
        fun loadReplacementsForId(id: Long)
    }

    interface View : BaseContract.View {
        fun showProgressIndicator()
        fun hideProgressIndicator()
        fun getWindowToken(): IBinder
        fun updateRecyclerView(adapter: ReplacementListAdapter)
        fun showNoDrugsFoundText()
        fun startDetailsFragment(drugModel: DrugModel)
        fun showSessionExpiredDialog()
    }
}