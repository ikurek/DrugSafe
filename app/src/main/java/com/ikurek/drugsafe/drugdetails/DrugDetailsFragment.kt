package com.ikurek.drugsafe.drugdetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.mainactivity.MainActivity
import com.ikurek.drugsafe.model.DrugModel
import com.ikurek.drugsafe.replacementslist.ReplacementListFragment
import kotlinx.android.synthetic.main.fragment_drug_details.*
import javax.inject.Inject

class DrugDetailsFragment : Fragment(), DrugDetailsContract.View {

    @Inject
    lateinit var presenter: DrugDetailsContract.Presenter

    lateinit var drugModel: DrugModel
    var shouldShowFAB: Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drug_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BaseApp.fragmentComponent.inject(this)
        presenter.attach(this)
        bindRecyclerView()
        bindFAB()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun onStart() {
        super.onStart()
        BaseApp.currentlyVisibleFragmentTag = getString(R.string.fragment_tag_searchdrugs)
    }

    override fun startSearchReplacementsFragment() {
        val parentActivity = this.activity as MainActivity
        parentActivity.changeFragment(
            ReplacementListFragment.instantiateWithDrugId(drugModel.id),
            getString(R.string.fragment_tag_replacementslist),
            true
        )
    }

    override fun switchFabMenu() {
        fab_menu.close(true)
        if (presenter.isDrugSaved(drugModel)) {
            fab_menu.inflate(R.menu.drug_details_saved_fab)
        } else {
            fab_menu.inflate(R.menu.drug_details_unsaved_fab)
        }
    }

    override fun getDrug(): DrugModel = this.drugModel

    private fun bindRecyclerView() {
        recyclerview.layoutManager = LinearLayoutManager(this.context)
        recyclerview.adapter = DrugDetailsAdapter(drugModel, presenter.getDrugModelFieldMap())
    }

    private fun bindFAB() {
        if (presenter.isDrugSaved(drugModel)) {
            fab_menu.inflate(R.menu.drug_details_saved_fab)
            fab_menu.setOnActionSelectedListener(this.presenter)
        } else {
            fab_menu.inflate(R.menu.drug_details_unsaved_fab)
            fab_menu.setOnActionSelectedListener(this.presenter)
        }
    }


    companion object {
        fun instantiateWithDrugModel(
            drugModel: DrugModel,
            shouldShowFAB: Boolean
        ): DrugDetailsFragment = DrugDetailsFragment().apply {
            this.drugModel = drugModel
            this.shouldShowFAB = shouldShowFAB
        }
    }


}
