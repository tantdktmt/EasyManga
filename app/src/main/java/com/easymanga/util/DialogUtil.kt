package com.easymanga.util

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.easymanga.R

class DialogUtil {

    companion object {

        fun showMessageDialog(context: Context, resMessage: Int, listener: DialogInterface.OnClickListener) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.alert_title_notification)
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(resMessage)
                .setPositiveButton(android.R.string.ok, listener)
            builder.create().show()
        }

        fun showMessageDialog(context: Context, resMessage: Int) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.alert_title_notification)
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(resMessage)
                .setPositiveButton(android.R.string.ok, null)
            builder.create().show()
        }
    }
}