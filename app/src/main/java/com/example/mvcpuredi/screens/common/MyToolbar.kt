package com.example.mvcpuredi.screens.common

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.mvcpuredi.R

class MyToolbar : Toolbar {
    interface NavigateUpListener {
        fun onNavigationUpClicked()
    }

    interface ViewModelListener {
        fun onViewModelClicked()
    }

    private var navigateUpListener: () -> Unit = {}
    private var viewModelListener: () -> Unit = {}

    private lateinit var navigateUp: FrameLayout
    private lateinit var viewModel: TextView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        init(context)
    }

    @SuppressLint("MissingInflatedId")
    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.my_toolbar, this, true)
        setContentInsetsRelative(0, 0)
        navigateUp = view.findViewById(R.id.navigate_up)
        navigateUp.setOnClickListener { navigateUpListener.invoke() }
        viewModel = view.findViewById(R.id.viewModel)
        viewModel.setOnClickListener {
            viewModelListener.invoke()
        }
    }

    fun setNavigateUpListener(navigateUpListener: () -> Unit) {
        this.navigateUpListener = navigateUpListener
        navigateUp.visibility = View.VISIBLE
    }

    fun setViewModelListener(viewModelListener: () -> Unit) {
        this.viewModelListener = viewModelListener
        viewModel.visibility = View.VISIBLE
    }
}