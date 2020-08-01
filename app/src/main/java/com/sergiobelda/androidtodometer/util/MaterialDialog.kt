/*
 * Copyright 2020 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.androidtodometer.util

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sergiobelda.androidtodometer.R

/**
 * MaterialDialog Util class to create a Material Dialog.
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

        fun AlertDialog.Builder.positiveButton(@StringRes resId: Int, handleClick: (which: Int) -> Unit = {}) {
            this.setPositiveButton(resId) { dialogInterface, which -> handleClick(which) }
        }

        fun AlertDialog.Builder.negativeButton(text: String, handleClick: (which: Int) -> Unit = {}) {
            this.setNegativeButton(text) { dialogInterface, which -> handleClick(which) }
        }

        fun AlertDialog.Builder.icon(@DrawableRes iconId: Int) {
            this.setIcon(iconId)
        }

        fun AlertDialog.Builder.message(message: CharSequence) {
            this.setMessage(message)
        }

        fun AlertDialog.Builder.message(@StringRes resId: Int) {
            this.setMessage(context.getString(resId))
        }

        fun AlertDialog.Builder.singleChoiceItems(items: Array<CharSequence>, checkedItem: Int, handleClick: (which: Int) -> Unit = {}) {
            this.setSingleChoiceItems(items, checkedItem) { dialogInterface, which -> handleClick(which) }
        }

        fun AlertDialog.Builder.title(title: String) {
            this.setTitle(title)
        }

        fun AlertDialog.Builder.title(@StringRes titleId: Int) {
            this.setTitle(titleId)
        }
    }
}
