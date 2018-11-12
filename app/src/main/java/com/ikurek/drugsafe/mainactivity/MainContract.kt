package com.ikurek.drugsafe.mainactivity

import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.ikurek.drugsafe.base.BaseContract

interface MainContract {

    interface Presenter : BaseContract.Presenter<MainContract.View>,
        NavigationView.OnNavigationItemSelectedListener

    interface View : BaseContract.View {
        fun openDrawer()
        fun closeDrawer()
        fun drawerIsOpen(): Boolean
        fun changeFragment(fragment: Fragment, tag: String, addToBackstack: Boolean)
        fun setupNavigationDrawer()
        fun showExitDialog()
        fun updateMainActivityUIByCurrentFragmentTag()
    }
}