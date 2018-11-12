package com.ikurek.drugsafe.mydrugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.mainactivity.MainActivity
import com.ikurek.drugsafe.seachdrugs.SearchDrugsFragment
import kotlinx.android.synthetic.main.fragment_my_drugs.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class MyDrugsFragment : Fragment(), MyDrugsContract.View {

    @Inject
    lateinit var presenter: MyDrugsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_drugs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BaseApp.fragmentComponent.inject(this)
        presenter.attach(this)
        bindFAB()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun onStart() {
        super.onStart()
        BaseApp.currentlyVisibleFragmentTag = getString(R.string.fragment_tag_mydrugs)
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun setItems(items: List<String>) {
    }

    override fun startSearchDrugFragment() {
        val parentActivity = this.activity as MainActivity
        parentActivity.changeFragment(
            SearchDrugsFragment(),
            getString(R.string.fragment_tag_searchdrugs),
            true
        )
    }

    private fun bindFAB() {
        fab.setOnClickListener { presenter.handleFloatingActionButton() }
    }


}
