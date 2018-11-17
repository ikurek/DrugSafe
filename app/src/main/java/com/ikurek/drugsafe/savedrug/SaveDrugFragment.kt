package com.ikurek.drugsafe.savedrug


import android.app.TimePickerDialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.model.DrugModel
import kotlinx.android.synthetic.main.fragment_save_drug.*
import javax.inject.Inject

class SaveDrugFragment : Fragment(), SaveDrugContract.View {

    @Inject
    lateinit var presenter: SaveDrugContract.Presenter

    lateinit var drugModel: DrugModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save_drug, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BaseApp.fragmentComponent.inject(this)
        presenter.attach(this)
        bindDrug()
        bindUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun onStart() {
        super.onStart()
        BaseApp.currentlyVisibleFragmentTag = getString(R.string.fragment_tag_savedrug)
    }

    override fun setSelectedTime(hours: Int, minutes: Int) {

        // I'm really tired, sorry
        val timeToSet = when {
            hours < 10 && minutes < 10 -> "0$hours:0$minutes"
            hours >= 10 && minutes < 10 -> "$hours:0$minutes"
            hours < 10 && minutes >= 10 -> "0$hours:$minutes"
            else -> "$hours:$minutes"
        }

        time_text.text = SpannableStringBuilder(timeToSet)
    }

    private fun bindDrug() {
        name.text = drugModel.name
        val dosageToSet = "${drugModel.ammountOfSubstance}, ${drugModel.type}"
        dosage.text = dosageToSet
    }

    private fun bindUI() {
        time_select_button.setOnClickListener {
            TimePickerDialog(this.context, presenter, 12, 0, true).show()
        }
        day_picker.setOnWeekdaysChangeListener(presenter)
        notification_check_box.setOnCheckedChangeListener(presenter)
        save_button.setOnClickListener {
            presenter.saveScheduledDrug(drugModel)
            fragmentManager!!.popBackStack()
        }
    }

    companion object {
        fun instantiateWithDrugModel(drugModel: DrugModel): SaveDrugFragment =
            SaveDrugFragment().apply {
                this.drugModel = drugModel
            }
    }
}
