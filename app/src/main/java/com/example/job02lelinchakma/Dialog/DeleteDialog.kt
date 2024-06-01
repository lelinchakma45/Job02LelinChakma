package com.example.job02lelinchakma.Dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.job02lelinchakma.Models.UserProfile
import com.example.job02lelinchakma.R

class DeleteDialog(
    context: Context,
    private val userProfile: UserProfile,
    private val onDeleteConfirmed: (Boolean) -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.delete_dialog)

        window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        findViewById<TextView>(R.id.dialog_title).text = "Are you sure you want to delete this profile?"
        findViewById<Button>(R.id.delete_button).setOnClickListener {
            onDeleteConfirmed(true)
            dismiss()
        }
        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            onDeleteConfirmed(false)
            dismiss()
        }
    }
}