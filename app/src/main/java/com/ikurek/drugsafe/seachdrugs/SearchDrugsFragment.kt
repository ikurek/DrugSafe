package com.ikurek.drugsafe.seachdrugs

import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.utlis.Validators
import kotlinx.android.synthetic.main.fragment_search_drugs.*
import javax.inject.Inject

class SearchDrugsFragment : Fragment(), SearchDrugsContract.View {

    @Inject
    lateinit var presenter: SearchDrugsContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_drugs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BaseApp.fragmentComponent.inject(this)
        presenter.attach(this)
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

    override fun updateRecyclerView(adapter: SearchDrugsAdapter) {
        recyclerview.layoutManager = LinearLayoutManager(this.context)
        recyclerview.addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
        recyclerview.adapter = adapter
        recyclerview.visibility = View.VISIBLE
    }

    override fun clearRecyclerView() {
        recyclerview.visibility = View.INVISIBLE
        recyclerview.adapter = null
    }

    override fun showProgressIndicator() {
        progress_indicator.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        progress_indicator.visibility = View.GONE
    }

    override fun showBackgroundText() {
        textview_background.text = getString(R.string.type_drug_or_substance_first)
        textview_background.visibility = View.VISIBLE
    }

    override fun hideBackgroundText() {
        textview_background.text = getString(R.string.type_drug_or_substance_first)
        textview_background.visibility = View.INVISIBLE
    }

    override fun showNoDrugsFoundText() {
        textview_background.text = getString(R.string.error_no_drugs_found)
        textview_background.visibility = View.VISIBLE
    }

    override fun setItems(items: List<String>) {
    }

    override fun getWindowToken(): IBinder {
        return search_drugs_layout.windowToken
    }

    private fun bindHandlers() {
        search_drugs_button.setOnClickListener {
            if (areRequiredFieldsValid()) presenter.handleSearchClick(search_drugs_text.text.toString())
        }

        search_drugs_text.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && areRequiredFieldsValid()) {
                presenter.handleSearchClick(search_drugs_text.text.toString())
            }
            true
        }
    }

    private fun areRequiredFieldsValid(): Boolean {
        return if (!Validators.isDrugQueryValid(search_drugs_text.text.toString())) {
            search_drugs_text.error = getString(R.string.error_drug_query_too_short)
            false
        } else {
            search_drugs_text.error = null
            true
        }
    }
}