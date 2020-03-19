package com.sergiobelda.androidtodometer.util

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sergiobelda.androidtodometer.R

/**
 * MaterialDialog
 * @author Sergio Belda Galbis
 */
class MaterialDialog {
    companion object {
        inline fun createDialog(context: Context, dialogBuilder: AlertDialog.Builder.() -> Unit): AlertDialog {
            val builder = MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered)
                .setTitle(" ")
            builder.dialogBuilder()
            return builder.create()
        }

        fun AlertDialog.Builder.positiveButton(text: String, handleClick: (which: Int) -> Unit = {}) {
            this.setPositiveButton(text) { dialogInterface, which -> handleClick(which) }
        }

        fun AlertDialog.Builder.negativeButton(text: String, handleClick: (which: Int) -> Unit = {}) {
            this.setNegativeButton(text) { dialogInterface, which -> handleClick(which) }
        }

        fun AlertDialog.Builder.icon(@DrawableRes iconId : Int) {
            this.setIcon(iconId)
        }

        fun AlertDialog.Builder.message(message: String) {
            this.setMessage(message)
        }
    }
}