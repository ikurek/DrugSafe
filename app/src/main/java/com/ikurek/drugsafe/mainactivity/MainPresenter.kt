package com.ikurek.drugsafe.mainactivity

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.barcodescanner.BarcodeScannerFragment
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.mydrugs.MyDrugsFragment
import com.ikurek.drugsafe.seachdrugs.SearchDrugsFragment
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
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

            R.id.nav_scan -> {
                view?.requestCameraPermission(cameraPermissionListener)
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

    private val cameraPermissionListener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted(response: PermissionGrantedResponse) {
            view?.changeFragment(
                BarcodeScannerFragment(),
                context.getString(R.string.fragment_tag_scanbarcode),
                true
            )
        }

        override fun onPermissionDenied(response: PermissionDeniedResponse) {
            //TODO: Handle
        }

        override fun onPermissionRationaleShouldBeShown(
            permission: PermissionRequest,
            token: PermissionToken
        ) {
            //TODO: Handle
        }

    }
}