package com.ikurek.drugsafe.replacementslist

import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.drugdetails.DrugDetailsFragment
import com.ikurek.drugsafe.mainactivity.MainActivity
import com.ikurek.drugsafe.model.DrugModel
import com.ikurek.drugsafe.utlis.Session
import kotlinx.android.synthetic.main.fragment_replacement_list.*
import javax.inject.Inject

class ReplacementListFragment : Fragment(), ReplacementListContract.View {

    @Inject
    lateinit var presenter: ReplacementListContract.Presenter

    var drugId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BaseApp.fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_replacement_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.loadReplacementsForId(drugId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    override fun onStart() {
        super.onStart()
        BaseApp.currentlyVisibleFragmentTag = getString(R.string.fragment_tag_replacementslist)
    }

    override fun updateRecyclerView(adapter: ReplacementListAdapter) {
        recyclerview.layoutManager = LinearLayoutManager(this.context)
        recyclerview.addItemDecoration(
            DividerItemDecoration(
                this.context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerview.adapter = adapter
        recyclerview.visibility = View.VISIBLE
    }

    override fun showProgressIndicator() {
        progress_indicator.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        progress_indicator.visibility = View.GONE
    }

    override fun showNoDrugsFoundText() {
        textview_background.text = getString(R.string.error_no_drugs_found)
        textview_background.visibility = View.VISIBLE
    }

    override fun startDetailsFragment(drugModel: DrugModel) {
        val parentActivity = this.activity as MainActivity
        parentActivity.changeFragment(
            DrugDetailsFragment.instantiateWithDrugModel(
                drugModel
            ), getString(R.string.fragment_tag_drugdetails), true
        )
    }

    override fun showSessionExpiredDialog() {
        MaterialDialog(context!!).apply {
            title(R.string.session_expired)
            message(R.string.error_session_expired)
            positiveButton {
                it.dismiss()
                Session.signOut(context)
            }
            onDismiss {
                Session.signOut(context)
            }
        }.show()
    }

    override fun getWindowToken(): IBinder {
        return replacement_list_layout.windowToken
    }

    companion object {
        fun instantiateWithDrugId(drugId: Long): ReplacementListFragment =
            ReplacementListFragment().apply {
                this.drugId = drugId
            }
    }
}