package com.example.mvcpuredi.screens

import androidx.fragment.app.Fragment
import com.example.mvcpuredi.Injector
import com.example.mvcpuredi.PresentationCompositionRoot

open class BaseFragment : Fragment() {
    private val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }

    protected val injector get() = Injector(compositionRoot)
}