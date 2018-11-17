package com.ikurek.drugsafe.savedrug

import android.app.TimePickerDialog
import android.widget.CompoundButton
import com.dpro.widgets.OnWeekdaysChangeListener
import com.ikurek.drugsafe.base.BaseContract
import com.ikurek.drugsafe.model.DrugModel

interface SaveDrugContract {

    interface Presenter : BaseContract.Presenter<SaveDrugContract.View>,
        TimePickerDialog.OnTimeSetListener, OnWeekdaysChangeListener,
        CompoundButton.OnCheckedChangeListener {

        fun saveScheduledDrug(drugModel: DrugModel)
    }

    interface View : BaseContract.View {

        fun setSelectedTime(hours: Int, minutes: Int)
    }

}