package com.ikurek.drugsafe.replacementslist

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.api.DrugsApi
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.model.DrugModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ReplacementListPresenter : ReplacementListContract.Presenter {

    @Inject
    lateinit var drugsApi: DrugsApi

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var context: Context

    var view: ReplacementListContract.View? = null

    var replacements: List<DrugModel> = listOf()

    override fun attach(view: ReplacementListContract.View) {
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        this.view = null
    }

    override fun loadReplacementsForId(id: Long) {

        if (replacements.isEmpty()) {
            getReplacements(id)
        } else {
            view?.updateRecyclerView(ReplacementListAdapter(replacements) { itemClicked(it) })
        }

    }


    private fun getReplacements(id: Long) {
        // Read token
        val token = sharedPreferences.getString(context.getString(R.string.sp_key_auth_token), "")

        // Stop if token doesn't exist
        // TODO: Deauth here

        // Show progress
        view?.showProgressIndicator()
        drugsApi.getDrugReplacementsById(token!!, id).enqueue(object : Callback<List<DrugModel>> {
            override fun onFailure(call: Call<List<DrugModel>>, t: Throwable) {
                Log.e("SearchReplacements", "Failed. Reason: $t")
                Log.e("SearchReplacements", "Sent ${call.request()}")


                view?.hideProgressIndicator()
                view?.showNoDrugsFoundText()
            }

            override fun onResponse(
                call: Call<List<DrugModel>>,
                response: Response<List<DrugModel>>
            ) {
                Log.d("SearchDrugs", "Success. Response: $response ")

                when (response.code()) {
                    200 -> {
                        replacements =
                                response.body()!!.sortedWith(
                                    compareBy(
                                        DrugModel::name,
                                        DrugModel::ammountOfSubstance
                                    )
                                )
                        view?.hideProgressIndicator()
                        view?.updateRecyclerView(ReplacementListAdapter(replacements) {
                            itemClicked(
                                it
                            )
                        })
                    }
                    403 -> {
                        view?.hideProgressIndicator()
                        view?.showSessionExpiredDialog()
                    }
                    404 -> {
                        view?.hideProgressIndicator()
                        view?.showNoDrugsFoundText()
                    }
                }


            }

        })
    }

    private fun itemClicked(drugModel: DrugModel) {
        view?.startDetailsFragment(drugModel)
    }

}