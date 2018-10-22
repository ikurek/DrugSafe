package com.ikurek.drugsafe.mainactivity

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.di.components.DaggerActivityComponent
import com.ikurek.drugsafe.di.modules.ActivityModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule())
            .build()
            .inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        injectDependencies()
        setupNavigationDrawer()
        setupFloatingActionButton()
        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onBackPressed() {
        when {
            drawerIsOpen() -> closeDrawer()
            supportFragmentManager.backStackEntryCount == 0 -> showExitDialog()
            else -> {
                super.onBackPressed()
                nav_view.setCheckedItem(R.id.nav_my_drugs)
                updateToolbarByFragmentTag("MyDrugsFragment")
                updateNavigationViewByFragmentTag("MyDrugsFragment")
                updateFloatingActionButtonByFragmentTag("MyDrugsFragment")
            }
        }
    }

    override fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    override fun openDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)
    }

    override fun drawerIsOpen(): Boolean = drawer_layout.isDrawerOpen(GravityCompat.START)

    override fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_frame, fragment)
            .addToBackStack(tag)
            .commit()
        updateToolbarByFragmentTag(tag)
        updateNavigationViewByFragmentTag(tag)
        updateFloatingActionButtonByFragmentTag(tag)
    }

    override fun setupNavigationDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setCheckedItem(R.id.nav_my_drugs)
        nav_view.setNavigationItemSelectedListener(presenter)
    }

    override fun setupFloatingActionButton() {
        fab.setOnClickListener { presenter.handleFloatingActionButton() }
    }

    override fun updateToolbarByFragmentTag(tag: String) {
        when (tag) {
            "MyDrugsFragment" -> toolbar.title = getString(R.string.my_drugs)
            "SearchDrugsFragment" -> toolbar.title = getString(R.string.search_drugs)
        }
    }

    override fun updateNavigationViewByFragmentTag(tag: String) {
        when (tag) {
            "MyDrugsFragment" -> nav_view.setCheckedItem(R.id.nav_my_drugs)
            "SearchDrugsFragment" -> nav_view.setCheckedItem(R.id.nav_search_drugs)
        }
    }

    override fun updateFloatingActionButtonByFragmentTag(tag: String) {
        when (tag) {
            "MyDrugsFragment" -> fab.show()
            "SearchDrugsFragment" -> fab.hide()
        }
    }

    override fun showExitDialog() {
        MaterialDialog(this)
            .title(R.string.dialog_exit_title)
            .message(R.string.dialog_exit_body)
            .positiveButton { this.finishAffinity() }
            .negativeButton { dialog -> dialog.dismiss() }
            .show()
    }
}
