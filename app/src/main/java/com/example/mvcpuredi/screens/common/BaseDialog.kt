package com.example.mvcpuredi.screens.common

import androidx.fragment.app.DialogFragment

open class BaseDialog: DialogFragment() {
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
    }

    protected val injector get() = presentationComponent
}