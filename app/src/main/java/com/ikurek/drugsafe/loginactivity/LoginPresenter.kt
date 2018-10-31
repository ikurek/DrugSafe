package com.ikurek.drugsafe.loginactivity


import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.ikurek.drugsafe.api.ApiInterface
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.model.LoginModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginPresenter : LoginContract.Presenter {

    var view: LoginContract.View? = null

    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var context: Context

    override fun attach(view: LoginContract.View) {
        this.view = view
        BaseApp.presenterComponent.inject(this)
    }

    override fun detach() {
        this.view = null
    }

    override fun handleLoginButton(email: String, password: String) {
        // Hide keyboard
        val inputMethodManager: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.getWindowToken(), 0)

        //Show progress
        view?.showProgressIndicator()

        // FIXME: Validate
        val json: JsonObject = JsonParser().parse("{\"email\": \"$email\", \"password\": \"$password\" }").asJsonObject

        // Make API call
        apiInterface.login(json).enqueue(object : Callback<LoginModel> {
            override fun onFailure(call: Call<LoginModel>, e: Throwable) {
                Log.e("Login", "Login failed. Cause: $e")
                view?.hideProgressIndicator()
                view?.showServerOfflineDialog()
            }

            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                Log.d("Login", "Success. token is ${response.raw().headers()}")

                view?.hideProgressIndicator()

                when (response.code()) {
                    200 -> {
                        TODO("Not yet implemented")
                    }
                    404 -> view?.showServerOfflineDialog()
                }
            }
        })
    }

    override fun handleRegisterButton() {
        view?.startRegisterActivity()
    }
}