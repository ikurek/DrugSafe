package com.ikurek.drugsafe.mainactivity

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.mydrugs.MyDrugsFragment
import com.ikurek.drugsafe.seachdrugs.SearchDrugsFragment
import javax.inject.Inject

class MainPresenter : MainContract.Presenter, NavigationView.OnNavigationItemSelectedListener {

    var view: MainContract.View? = null

    @Inject
    lateinit var context: Context

    var myDrugsFragment = MyDrugsFragment()

    override fun attach(view: MainContract.View) {
        Log.d("MainPresenter", "Attached")
        this.view = view
        BaseApp.presenterComponent.inject(this)
        view.changeFragment(myDrugsFragment, context.getString(R.string.fragment_tag_mydrugs), true)
    }

    override fun detach() {
        Log.d("MainPresenter", "Detached")
        this.view = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_my_drugs -> {
                view?.changeFragment(myDrugsFragment, context.getString(R.string.fragment_tag_mydrugs), true)
            }
            R.id.nav_search_drugs -> {
                view?.changeFragment(
                    SearchDrugsFragment(),
                    context.getString(R.string.fragment_tag_searchdrugs),
                    true
                )
            }
            R.id.nav_settings -> {

            }

            R.id.nav_signout -> {
                view?.signOut()
            }
        }

        view?.closeDrawer()
        return true
    }
}