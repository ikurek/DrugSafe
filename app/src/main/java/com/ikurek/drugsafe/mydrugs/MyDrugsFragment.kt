package com.ikurek.drugsafe.mydrugs

import android.app.Dialog
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.di.components.DaggerFragmentComponent
import com.ikurek.drugsafe.di.modules.FragmentModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class MyDrugsFragment : Fragment(), MyDrugsContract.View {

    @Inject
    lateinit var presenter : MyDrugsContract.Presenter

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
        return inflater.inflate(R.layout.fragment_my_drugs, container, false)
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
