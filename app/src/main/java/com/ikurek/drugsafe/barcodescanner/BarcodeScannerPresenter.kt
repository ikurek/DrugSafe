package com.ikurek.drugsafe.barcodescanner

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.api.DrugsApi
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.model.DrugModel
import com.ikurek.drugsafe.utlis.Validators
import de.klimek.scanner.OnDecodedCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BarcodeScannerPresenter : BarcodeScannerContract.Presenter, OnDecodedCallback {

    @Inject
    lateinit var drugsApi: DrugsApi

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var context: Context

    var view: BarcodeScannerContract.View? = null

    override fun attach(view: BarcodeScannerContract.View) {
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        this.view = null
    }

    override fun onDecoded(scanned: String?) {
        Log.d("BarcodeScanner", "Scanned: $scanned")
        if (Validators.isEanValid(scanned!!)) {
            view?.hideScanner()
            view?.showProgressIndicator()
            handleScannedEan(scanned.toLong())
        } else {
            view?.showDrugNotFoundDialog()
        }
    }

    private fun handleScannedEan(ean: Long) {

        // Read token
        val token = sharedPreferences.getString(context.getString(R.string.sp_key_auth_token), "")

        // Stop if token doesn't exist
        // TODO: Deauth here

        drugsApi.getDrugByPackageEan(token!!, ean).enqueue(object : Callback<DrugModel> {
            override fun onFailure(call: Call<DrugModel>, t: Throwable) {
                Log.e("SearchDrugs", "Failed. Reason: $t")
                Log.e("SearchDrugs", "Sent ${call.request()}")
                view?.hideProgressIndicator()
                view?.showScanner()
                view?.showConnectionFailedDialog()
            }

            override fun onResponse(call: Call<DrugModel>, response: Response<DrugModel>) {
                Log.d("SearchDrugs", "Success. Response: $response ")

                when (response.code()) {
                    200 -> {
                        val drug = response.body()!!
                        view?.hideProgressIndicator()
                        view?.startDetailsFragment(drug)
                    }
                    404 -> {
                        view?.hideProgressIndicator()
                        view?.showScanner()
                        view?.showDrugNotFoundDialog()
                    }
                }


            }

        })
    }

}