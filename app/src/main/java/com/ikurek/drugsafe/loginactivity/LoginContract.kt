package com.ikurek.drugsafe.loginactivity

import android.os.IBinder
import com.ikurek.drugsafe.base.BaseContract

interface LoginContract {

    interface Presenter : BaseContract.Presenter<LoginContract.View> {

        fun handleLoginButton(email: String, password: String)
        fun handleRegisterButton()

    }

    interface View : BaseContract.View {
        fun startRegisterActivity()
        fun getWindowToken(): IBinder
        fun hideProgressIndicator()
        fun showProgressIndicator()
        fun showIncorrectPasswordEmailCombinationDialog()
        fun showServerOfflineDialog()
        fun showConnectionFailedDialog()
        fun showInternalServerErrorDialog()
        fun startMainActivity()
    }
}