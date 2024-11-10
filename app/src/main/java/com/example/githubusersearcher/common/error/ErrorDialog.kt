package com.example.githubusersearcher.common.error

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.githubusersearcher.R

class ErrorDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.error_msg))
            .setIcon(R.drawable.ic_error)
            .setPositiveButton(getString(R.string.ok)) { _, _ ->
                dismiss()
            }
            .create()

    companion object {
        const val TAG = "ErrorDialog"
    }
}