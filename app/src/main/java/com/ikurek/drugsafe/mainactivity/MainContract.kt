package com.ikurek.drugsafe.mainactivity

import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.ikurek.drugsafe.base.BaseContract

interface MainContract {

    interface Presenter : BaseContract.Presenter<MainContract.View>, NavigationView.OnNavigationItemSelectedListener {
        fun handleFloatingActionButton()
    }

    interface View : BaseContract.View {
        fun openDrawer()
        fun closeDrawer()
        fun drawerIsOpen(): Boolean
        fun changeFragment(fragment: Fragment, tag: String)
        fun setupNavigationDrawer()
        fun setupFloatingActionButton()
        fun updateToolbarByFragmentTag(tag: String)
        fun updateNavigationViewByFragmentTag(tag: String)
        fun updateFloatingActionButtonByFragmentTag(tag: String)
        fun showExitDialog()
    }
}