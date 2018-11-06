package com.ikurek.drugsafe.seachdrugs

import android.os.IBinder
import com.ikurek.drugsafe.base.BaseContract

interface SearchDrugsContract {

    interface Presenter : BaseContract.Presenter<SearchDrugsContract.View> {
        fun onItemsLoaded()
        fun onItemClicked()
        fun handleSearchClick(query: String)
    }

    interface View : BaseContract.View {
        fun setItems(items: List<String>)
        fun showProgressIndicator()
        fun hideProgressIndicator()
        fun showBackgroundText()
        fun hideBackgroundText()
        fun getWindowToken(): IBinder
        fun updateRecyclerView(adapter: SearchDrugsAdapter)
        fun showNoDrugsFoundText()
        fun clearRecyclerView()
    }
}