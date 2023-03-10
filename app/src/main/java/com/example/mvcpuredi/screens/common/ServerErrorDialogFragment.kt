package com.example.mvcpuredi.screens.common

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle

class ServerErrorDialogFragment : BaseDialog() {
    override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog {
        return AlertDialog.Builder(activity).let {
            it.setTitle("Error")
            it.setMessage("Message")
            it.setPositiveButton("OK") { _, _ -> dismiss()}
            it.create()
        }
    }

    companion object {
        fun newInstance(): ServerErrorDialogFragment {
            return ServerErrorDialogFragment()
        }
    }
}