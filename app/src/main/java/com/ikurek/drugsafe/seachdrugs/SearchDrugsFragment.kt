package com.ikurek.drugsafe.seachdrugs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.di.components.DaggerFragmentComponent
import com.ikurek.drugsafe.di.modules.FragmentModule
import javax.inject.Inject

class SearchDrugsFragment : Fragment(), SearchDrugsContract.View {

    @Inject
    lateinit var presenter: SearchDrugsContract.Presenter

    private fun injectDependencies() {
        DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_drugs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()
        presenter.attach(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun setItems(items: List<String>) {
    }
}