package com.ikurek.drugsafe.loginactivity

import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.ikurek.drugsafe.R
import com.ikurek.drugsafe.base.BaseApp
import com.ikurek.drugsafe.registeractivity.RegisterActivity
import com.ikurek.drugsafe.utlis.Validators
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = getString(R.string.signin)
        BaseApp.activityComponent.inject(this)
        presenter.attach(this)
        bindHandlers()
    }

    override fun bindHandlers() {
        login_button.setOnClickListener {
            if (areRequiredFieldsValid()) presenter.handleLoginButton(email_text.text.toString(), password_text.text.toString())
        }
        goto_register_button.setOnClickListener { presenter.handleRegisterButton() }
    }

    override fun startRegisterActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun showServerOfflineDialog() {
        MaterialDialog(this).apply {
            title(R.string.error)
            message(R.string.error_server_offline)
            positiveButton { this.dismiss() }
        }.show()
    }

    override fun showIncorrectPasswordDialog() {
        MaterialDialog(this).apply {
            title(R.string.error)
            message(R.string.success_registration)
            positiveButton { this.dismiss() }
        }.show()
    }

    override fun showProgressIndicator() {
        login_button.visibility = View.INVISIBLE
        goto_register_button.visibility = View.INVISIBLE
        progress_indicator.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        progress_indicator.visibility = View.INVISIBLE
        login_button.visibility = View.VISIBLE
        goto_register_button.visibility = View.VISIBLE
    }

    private fun areRequiredFieldsValid(): Boolean {
        if (!Validators.isEmailValid(email_text.text.toString())) {
            email_input_layout.error = getString(R.string.error_email_invalid)
            return false
        } else {
            email_input_layout.error = null
        }

        if (!Validators.isPasswordLongerThenSixChars(password_text.text.toString())) {
            password_input_layout.error = getString(R.string.error_password_too_short)
            return false
        } else {
            password_input_layout.error = null
        }

        return true
    }

    override fun getWindowToken(): IBinder {
        return login_layout.windowToken
    }
}