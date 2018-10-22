package com.ikurek.drugsafe.mainactivity

import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import android.view.MenuItem
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.mydrugs.MyDrugsFragment
import com.ikurek.drugsafe.seachdrugs.SearchDrugsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter : MainContract.Presenter, NavigationView.OnNavigationItemSelectedListener {

    var view: MainContract.View? = null

    override fun attach(view: MainContract.View) {
        this.view = view
        view.changeFragment(MyDrugsFragment(), "MyDrugsFragment")
    }

    override fun detach() {
        this.view = null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_my_drugs -> {
                // Handle the camera action
            }
            R.id.nav_search_drugs -> {
                view?.changeFragment(SearchDrugsFragment(), "SearchDrugsFragment")
            }
            R.id.nav_settings -> {

            }
        }

        view?.closeDrawer()
        return true
    }

    override fun handleFloatingActionButton() {
        view?.changeFragment(SearchDrugsFragment(), "SearchDrugsFragment")
    }

}