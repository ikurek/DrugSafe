package com.ikurek.drugsafe.savedrug

import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.widget.CompoundButton
import android.widget.TimePicker
import com.dpro.widgets.OnWeekdaysChangeListener
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.database.AppDatabase
import com.ikurek.drugsafe.model.DrugModel
import com.ikurek.drugsafe.model.ScheduledDrugModel
import javax.inject.Inject

class SaveDrugPresenter : SaveDrugContract.Presenter,
    TimePickerDialog.OnTimeSetListener, OnWeekdaysChangeListener,
    CompoundButton.OnCheckedChangeListener {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var appDatabase: AppDatabase

    var view: SaveDrugContract.View? = null

    var selectedHour: Int = -1
    var selectedMinutes: Int = -1
    var selectedDays: List<Int> = listOf()
    var shouldNotify: Boolean = false

    override fun attach(view: SaveDrugContract.View) {
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        this.view = null
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        this.selectedHour = hourOfDay
        this.selectedMinutes = minute
        this.view?.setSelectedTime(selectedHour, selectedMinutes)

    }

    override fun onChange(view: View?, day: Int, selectedDays: MutableList<Int>?) {
        this.selectedDays = selectedDays!!.toList()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        shouldNotify = isChecked
    }

    override fun saveScheduledDrug(drugModel: DrugModel) {
        var scheduledDrugModel = ScheduledDrugModel(
            scheduled_drug_id = null,
            drug = drugModel,
            days = selectedDays,
            hour = selectedHour,
            minutes = selectedMinutes,
            notify = shouldNotify
        )

        appDatabase.scheduledDrugDAO().save(scheduledDrugModel)
    }

}