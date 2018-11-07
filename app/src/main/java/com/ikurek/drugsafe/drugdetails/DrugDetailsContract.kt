package com.ikurek.drugsafe.drugdetails

import com.ikurek.drugsafe.base.BaseContract

interface DrugDetailsContract {

    interface Presenter : BaseContract.Presenter<DrugDetailsContract.View>

    interface View : BaseContract.View
}