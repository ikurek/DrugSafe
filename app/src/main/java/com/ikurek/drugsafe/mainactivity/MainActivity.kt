package com.ikurek.drugsafe.mainactivity

import android.Manifest
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.utlis.Session
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setTitle(R.string.my_drugs)
        BaseApp.activityComponent.inject(this)
        setupNavigationDrawer()
        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onBackPressed() {
        when {
            drawerIsOpen() -> closeDrawer()
            supportFragmentManager.backStackEntryCount == 1 -> showExitDialog()
            BaseApp.currentlyVisibleFragmentTag == getString(R.string.fragment_tag_mydrugs) -> showExitDialog()
            else -> {
                super.onBackPressed()
            }
        }

        updateMainActivityUIByCurrentFragmentTag()
    }

    override fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    override fun openDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)
    }

    override fun drawerIsOpen(): Boolean = drawer_layout.isDrawerOpen(GravityCompat.START)

    override fun changeFragment(fragment: Fragment, tag: String, addToBackstack: Boolean) {

        if (BaseApp.currentlyVisibleFragmentTag != tag) {
            Log.d("MainActivity", "Fragment ${BaseApp.currentlyVisibleFragmentTag} -> $tag")


            // Swap fragment with/without backstack
            if (addToBackstack) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_frame, fragment)
                    .addToBackStack(tag)
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_frame, fragment)
                    .commit()
            }

            // Save fragment tag as current
            BaseApp.currentlyVisibleFragmentTag = tag

        } else {
            Log.d("MainActivity", "Fragment ${BaseApp.currentlyVisibleFragmentTag} == $tag")
        }

        // Update UI elements
        updateMainActivityUIByCurrentFragmentTag()

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

        // Starting state
        nav_view.setCheckedItem(R.id.nav_my_drugs)

        // Handle nav in presenter
        nav_view.setNavigationItemSelectedListener(presenter)

        // Setting logged user email
        val email = sharedPreferences.getString(getString(R.string.sp_key_user_email), "")
        nav_view.getHeaderView(0).nav_header_email.text = email
    }

    override fun showExitDialog() {
        MaterialDialog(this)
            .title(R.string.dialog_exit_title)
            .message(R.string.dialog_exit_body)
            .positiveButton {
                this.finishAffinity()
                BaseApp.currentlyVisibleFragmentTag = ""
            }
            .negativeButton { dialog -> dialog.dismiss() }
            .show()
    }

    override fun updateMainActivityUIByCurrentFragmentTag() {
        when (BaseApp.currentlyVisibleFragmentTag) {
            getString(R.string.fragment_tag_mydrugs) -> {
                toolbar.title = getString(R.string.my_drugs)
                nav_view.setCheckedItem(R.id.nav_my_drugs)
            }
            getString(R.string.fragment_tag_searchdrugs) -> {
                nav_view.setCheckedItem(R.id.nav_search_drugs)
                toolbar.title = getString(R.string.search_drugs)
            }
            getString(R.string.fragment_tag_drugdetails) -> {
                toolbar.title = getString(R.string.drug_details)
            }
            getString(R.string.fragment_tag_replacementslist) -> {
                toolbar.title = getString(R.string.replacements)
            }
            getString(R.string.fragment_tag_scanbarcode) -> {
                nav_view.setCheckedItem(R.id.nav_scan)
                toolbar.title = getString(R.string.scan_barcode)
            }
            getString(R.string.fragment_tag_savedrug) -> {
                toolbar.title = getString(R.string.save_drug)
            }
        }
    }

    override fun requestCameraPermission(cameraPermissionListener: PermissionListener) {
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(cameraPermissionListener)
            .check()
    }

    override fun signOut() {
        this.finish()
        Session.signOut(this)
    }
}
