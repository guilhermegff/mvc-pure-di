package com.example.mvcpuredi.screens.common

import androidx.fragment.app.Fragment
import com.example.mvcpuredi.di.presentation.DaggerPresentationComponent
import com.example.mvcpuredi.di.presentation.PresentationModule

open class BaseFragment : Fragment() {
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .activityComponent((requireActivity() as BaseActivity).activityComponent)
            .presentationModule(PresentationModule())
            .build()
    }

    protected val injector get() = presentationComponent
}