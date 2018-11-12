package com.ikurek.drugsafe.seachdrugs

import android.os.IBinder
import com.ikurek.drugsafe.base.BaseContract
import com.ikurek.drugsafe.model.DrugModel

interface SearchDrugsContract {

    interface Presenter : BaseContract.Presenter<SearchDrugsContract.View> {
        fun handleSearchClick(query: String)
        fun loadLastSearchedDrugs()
    }

    interface View : BaseContract.View {
        fun showProgressIndicator()
        fun hideProgressIndicator()
        fun showBackgroundText()
        fun hideBackgroundText()
        fun getWindowToken(): IBinder
        fun updateRecyclerView(adapter: SearchDrugsAdapter)
        fun showNoDrugsFoundText()
        fun clearRecyclerView()
        fun startDetailsFragment(drugModel: DrugModel)
    }
}