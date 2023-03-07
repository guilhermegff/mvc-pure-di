package com.example.mvcpuredi.screens.common

import androidx.fragment.app.Fragment
import com.example.mvcpuredi.di.presentation.PresentationModule
import com.example.mvcpuredi.di.presentation.UseCasesModule

open class BaseFragment : Fragment() {
    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(PresentationModule(), UseCasesModule())
    }

    protected val injector get() = presentationComponent
}