package com.example.mvcpuredi.screens

import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    protected val compositionRoot get() = (requireActivity() as BaseActivity).compositionRoot
}