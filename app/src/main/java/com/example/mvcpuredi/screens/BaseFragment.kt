package com.example.mvcpuredi.screens

import androidx.fragment.app.Fragment
import com.example.mvcpuredi.PresentationCompositionRoot

open class BaseFragment : Fragment() {
    protected val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }
}