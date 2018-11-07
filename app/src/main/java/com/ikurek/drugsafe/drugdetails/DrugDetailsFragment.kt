package com.ikurek.drugsafe.drugdetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.model.DrugModel
import kotlinx.android.synthetic.main.fragment_drug_details.*
import javax.inject.Inject

class DrugDetailsFragment : Fragment(), DrugDetailsContract.View {

    @Inject
    lateinit var presenter: DrugDetailsContract.Presenter

    lateinit var drugModel: DrugModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drug_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BaseApp.fragmentComponent.inject(this)
        presenter.attach(this)
        bindRecyclerView()
        bindHandlers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun onStart() {
        super.onStart()
        BaseApp.currentlyVisibleFragmentTag = getString(R.string.fragment_tag_searchdrugs)
    }

    private fun bindRecyclerView() {
        recyclerview.layoutManager = LinearLayoutManager(this.context)
        recyclerview.adapter = DrugDetailsAdapter(drugModel)
    }

    private fun bindHandlers() {

    }

    companion object {
        fun instantiateWithDrugModel(drugModel: DrugModel): DrugDetailsFragment = DrugDetailsFragment().apply { this.drugModel = drugModel }
    }


}
