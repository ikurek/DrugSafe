package com.ikurek.drugsafe.seachdrugs

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.api.DrugsApi
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.model.DrugModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchDrugsPresenter : SearchDrugsContract.Presenter {

    @Inject
    lateinit var drugsApi: DrugsApi

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var context: Context

    var view: SearchDrugsContract.View? = null

    var drugs: List<DrugModel> = listOf()

    override fun attach(view: SearchDrugsContract.View) {
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        this.view = null
    }

    override fun loadLastSearchedDrugs() {
        if (drugs.isNotEmpty()) {
            view?.updateRecyclerView(SearchDrugsAdapter(drugs) { itemClicked(it) })
        } else {
            view?.showBackgroundText()
        }
    }

    override fun handleSearchClick(query: String) {
        // Hide keyboard
        val inputMethodManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.getWindowToken(), 0)

        // Read token
        val token = sharedPreferences.getString(context.getString(R.string.sp_key_auth_token), "")

        // Stop if token doesn't exist
        // TODO: Deauth here

        // Show progress
        view?.clearRecyclerView()
        view?.hideBackgroundText()
        view?.showProgressIndicator()
        drugsApi.getDrug(token!!, query).enqueue(object : Callback<List<DrugModel>> {
            override fun onFailure(call: Call<List<DrugModel>>, t: Throwable) {
                Log.e("SearchDrugs", "Failed. Reason: $t")
                Log.e("SearchDrugs", "Sent ${call.request()}")


                view?.hideProgressIndicator()
                view?.hideBackgroundText()
            }

            override fun onResponse(
                call: Call<List<DrugModel>>,
                response: Response<List<DrugModel>>
            ) {
                Log.d("SearchDrugs", "Success. Response: $response ")

                when (response.code()) {
                    200 -> {
                        drugs = response.body()!!.sortedWith(
                            compareBy(
                                DrugModel::name,
                                DrugModel::ammountOfSubstance
                            )
                        )
                        view?.hideProgressIndicator()
                        view?.updateRecyclerView(SearchDrugsAdapter(drugs) { itemClicked(it) })
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