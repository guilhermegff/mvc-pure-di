package com.example.mvcpuredi.screens

import androidx.fragment.app.Fragment
import com.example.mvcpuredi.DaggerPresentationComponent
import com.example.mvcpuredi.Injector
import com.example.mvcpuredi.PresentationModule

open class BaseFragment : Fragment() {
    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule((requireActivity() as BaseActivity).activityCompositionRoot))
            .build()
    }

    protected val injector get() = Injector(presentationComponent)
}