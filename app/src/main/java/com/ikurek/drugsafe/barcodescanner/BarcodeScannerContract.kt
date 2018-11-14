package com.ikurek.drugsafe.barcodescanner

import com.ikurek.drugsafe.base.BaseContract
import com.ikurek.drugsafe.model.DrugModel
import de.klimek.scanner.OnDecodedCallback

interface BarcodeScannerContract {

    interface Presenter : BaseContract.Presenter<BarcodeScannerContract.View>, OnDecodedCallback

    interface View : BaseContract.View {
        fun showProgressIndicator()
        fun hideProgressIndicator()
        fun showBackgroundText()
        fun hideBackgroundText()
        fun startDetailsFragment(drugModel: DrugModel)
    }
}