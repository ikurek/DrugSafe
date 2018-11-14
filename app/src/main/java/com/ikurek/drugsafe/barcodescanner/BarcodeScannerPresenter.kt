package com.ikurek.drugsafe.barcodescanner

import android.util.Log
import com.ikurek.drugsafe.api.DrugsApi
import com.ikurek.drugsafe.base.BaseApp
import de.klimek.scanner.OnDecodedCallback
import javax.inject.Inject

class BarcodeScannerPresenter : BarcodeScannerContract.Presenter, OnDecodedCallback {

    @Inject
    lateinit var drugsApi: DrugsApi

    var view: BarcodeScannerContract.View? = null

    override fun attach(view: BarcodeScannerContract.View) {
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        this.view = null
    }

    override fun onDecoded(p0: String?) {
        Log.d("BarcodeScanner", "Scanned: $p0")
    }
}