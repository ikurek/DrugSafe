package com.ikurek.drugsafe.barcodescanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.drugdetails.DrugDetailsFragment
import com.ikurek.drugsafe.mainactivity.MainActivity
import com.ikurek.drugsafe.model.DrugModel
import kotlinx.android.synthetic.main.fragment_scan_barcode.*
import javax.inject.Inject

class BarcodeScannerFragment : Fragment(), BarcodeScannerContract.View {

    @Inject
    lateinit var presenter: BarcodeScannerContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApp.fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scan_barcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        bindUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scanner_view.stopScanning()
        presenter.detach()
    }

    override fun onStart() {
        super.onStart()
        BaseApp.currentlyVisibleFragmentTag = getString(R.string.fragment_tag_scanbarcode)
        scanner_view.startScanning()
    }

    override fun onStop() {
        super.onStop()
        scanner_view.stopScanning()
    }

    override fun showProgressIndicator() {
        progress_indicator.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        progress_indicator.visibility = View.GONE
    }

    override fun hideScanner() {
        scanner_view.stopScanning()
        scanner_view.visibility = View.INVISIBLE
    }

    override fun showScanner() {
        scanner_view.startScanning()
        scanner_view.visibility = View.VISIBLE
    }

    override fun showDrugNotFoundDialog() {
        MaterialDialog(this.context!!).apply {
            title(R.string.error_no_drugs_found)
            message(R.string.error_ean_not_correct)
            positiveButton { this.dismiss() }
        }.show()
    }

    override fun showConnectionFailedDialog() {
        MaterialDialog(this.context!!).apply {
            title(R.string.error)
            message(R.string.error_connection_failed)
            positiveButton { this.dismiss() }
        }.show()
    }

    override fun startDetailsFragment(drugModel: DrugModel) {
        val parentActivity = this.activity as MainActivity
        parentActivity.changeFragment(
            DrugDetailsFragment.instantiateWithDrugModel(drugModel),
            getString(R.string.fragment_tag_drugdetails),
            true
        )
    }


    private fun bindUi() {
        scanner_view.setOnDecodedCallback(presenter)
    }
}