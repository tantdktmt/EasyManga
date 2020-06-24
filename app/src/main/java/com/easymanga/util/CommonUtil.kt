package com.easymanga.util

import android.content.Context
import android.os.Environment
import androidx.core.content.ContextCompat

class CommonUtil {

    companion object {

        fun isListEmpty(list: List<*>?): Boolean {
            return list == null || list.isEmpty()
        }

        fun getRootDirPath(context: Context): String {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                val file = ContextCompat.getExternalFilesDirs(context.applicationContext, null)[0]
                return file.absolutePath
            } else {
                return context.applicationContext.filesDir.absolutePath
            }
        }
    }
}