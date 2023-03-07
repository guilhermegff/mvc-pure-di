package com.example.mvcpuredi.screens.common

import androidx.fragment.app.Fragment
import com.example.mvcpuredi.di.presentation.DaggerPresentationComponent
import com.example.mvcpuredi.di.presentation.PresentationModule

open class BaseFragment : Fragment() {
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule((requireActivity() as BaseActivity).activityComponent))
            .build()
    }

    protected val injector get() = presentationComponent
}