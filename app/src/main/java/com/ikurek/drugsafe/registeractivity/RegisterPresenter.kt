package com.ikurek.drugsafe.registeractivity

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ikurek.drugsafe.api.ApiInterface
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.model.RegisterModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RegisterPresenter : RegisterContract.Presenter {

    var view: RegisterContract.View? = null

    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var context: Context

    override fun attach(view: RegisterContract.View) {
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        this.view = null
    }

    override fun handleRegisterButton(email: String, password: String, repeatPassword: String) {

        // Hide keyboard
        val inputMethodManager: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.getWindowToken(), 0)

        //Show progress
        view?.showProgressIndicator()

        // FIXME: Validate
        val json: JsonObject = JsonParser().parse("{\"email\": \"$email\", \"password\": \"$password\" }").asJsonObject

        // Make API call
        apiInterface.register(json).enqueue(object : Callback<RegisterModel> {

            override fun onFailure(call: Call<RegisterModel>, e: Throwable) {
                Log.e("Register Request", "Failed. Cause: $e")

                view?.hideProgressIndicator()
                view?.showServerOfflineDialog()

            }

            override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
                Log.d("Register Request", "Success. Response: ${response.body()}, ${response.raw().body()}, ${response.message()}")

                view?.hideProgressIndicator()

                when (response.code()) {
                    200 -> view?.showRegistrationSuccessfulDialog()
                    400 -> view?.showEmailAlreadyExistsDialog()
                    404 -> view?.showServerOfflineDialog()
                }

            }
        })
    }

}