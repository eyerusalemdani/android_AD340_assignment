package com.androiddemo.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import com.androiddemo.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogBox {

    companion object {

        fun dialogNoInternet(context: Context) {

            val builder = MaterialAlertDialogBuilder(context)
            //set title for alert dialog
            builder.setTitle(context.resources.getString(R.string.no_internet_connection))
            //set message for alert dialog
            builder.setMessage(context.resources.getString(R.string.check_your_network))

            //performing positive action
            builder.setPositiveButton(context.resources.getString(R.string.ok)) { dialogInterface, _ ->
                context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                dialogInterface.cancel()
            }

            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()

        }
    }
}