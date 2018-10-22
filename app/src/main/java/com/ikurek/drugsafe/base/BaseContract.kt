package com.ikurek.drugsafe.base

import android.app.Dialog

/**
 * Base for Contract classes
 * Contains interfaces for both
 * presenters and views
 */

class BaseContract {

    interface Presenter<in T> {
        fun attach(view: T)
        fun detach()
    }

    interface View {
    }
}