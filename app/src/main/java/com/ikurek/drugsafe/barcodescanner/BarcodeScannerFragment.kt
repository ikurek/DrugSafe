package com.ikurek.drugsafe.barcodescanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun showBackgroundText() {
        textview_background.text = getString(R.string.error_no_drugs_found)
        textview_background.visibility = View.VISIBLE
    }

    override fun hideBackgroundText() {
        textview_background.text = getString(R.string.type_drug_or_substance_first)
        textview_background.visibility = View.INVISIBLE
    }

    override fun startDetailsFragment(drugModel: DrugModel) {
        val parentActivity = this.activity as MainActivity
        parentActivity.changeFragment(
            DrugDetailsFragment.instantiateWithDrugModel(drugModel, true),
            getString(R.string.fragment_tag_drugdetails),
            true
        )
    }

    private fun bindUi() {
        scanner_view.setOnDecodedCallback(presenter)
    }
}